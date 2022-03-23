package com.jydev.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jydev.data.datasource.ImageThumbnailLocalDataSource
import com.jydev.data.datasource.ImageThumbnailLocalDataSourceImpl
import com.jydev.data.local.db.ImageThumbnailDataBase
import com.jydev.data.mock.LocalDataSourceMockFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ImageThumbnailLocalDataBaseTest {
    private lateinit var db : ImageThumbnailDataBase
    private lateinit var imageThumbnailLocalDataSource : ImageThumbnailLocalDataSource
    private val mockFactory = LocalDataSourceMockFactory()
    private val query = mockFactory.query

    @Before
    fun createDataSource(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,ImageThumbnailDataBase::class.java).build()
        imageThumbnailLocalDataSource = ImageThumbnailLocalDataSourceImpl(db,db.dao(),db.remoteKeyDao(),db.libraryDao())
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    fun getRemoteKeyFromQueryWhenInsertRemoteKeyTest() = runBlocking {
        val remoteKeyEntity = mockFactory.createRemoteKeyEntity()
        imageThumbnailLocalDataSource.insertRemoteKey(remoteKeyEntity)
        val remoteKeyFromQuery = imageThumbnailLocalDataSource.getLastRemoteKey(query)
        assert(remoteKeyEntity == remoteKeyFromQuery)
        db.clearAllTables()
    }

    @Test
    fun remoteKeyConflictReplaceWhenInsertRemoteKeyTest() = runBlocking {
        val remoteKeyEntity = mockFactory.createRemoteKeyEntity()
        imageThumbnailLocalDataSource.insertRemoteKey(remoteKeyEntity)
        var getLastInsertRemoteKeyEntity = imageThumbnailLocalDataSource.getLastRemoteKey(query)
        assert(remoteKeyEntity == getLastInsertRemoteKeyEntity)
        val conflictRemoteKeyEntity = remoteKeyEntity.copy(insertTimeDate = System.currentTimeMillis())
        imageThumbnailLocalDataSource.insertRemoteKey(conflictRemoteKeyEntity)
        getLastInsertRemoteKeyEntity = imageThumbnailLocalDataSource.getLastRemoteKey(query)
        assert(conflictRemoteKeyEntity == getLastInsertRemoteKeyEntity)
        db.clearAllTables()
    }

    @Test
    fun getLastRemoKeyReturnNullWhenInsertRemoteKeyAndThenDeleteRemoteKeyTest() = runBlocking{
        val remoteKeyEntity = mockFactory.createRemoteKeyEntity()
        imageThumbnailLocalDataSource.insertRemoteKey(remoteKeyEntity)
        var getLastInsertRemoteKeyEntity = imageThumbnailLocalDataSource.getLastRemoteKey(query)
        assert(getLastInsertRemoteKeyEntity == remoteKeyEntity)
        imageThumbnailLocalDataSource.deleteRemoteKeyFromQuery(query)
        getLastInsertRemoteKeyEntity = imageThumbnailLocalDataSource.getLastRemoteKey(query)
        assert(getLastInsertRemoteKeyEntity == null)
        db.clearAllTables()
    }

    @Test
    fun getImageThumbnailPagingSourceReturnPagingDataOrderByDatetimeDESCWhenInsertImageThumbnailEntityListTest()= runBlocking {
        val firstData = mockFactory.createImageThumbnailEntity("1")
        delay(10)
        val secondData = mockFactory.createImageThumbnailEntity("2")
        delay(10)
        val thirdData = mockFactory.createImageThumbnailEntity("3")
        val dataList = listOf(thirdData,secondData,firstData)
        imageThumbnailLocalDataSource.insertThumbnailList(dataList)
        val sortedPagingData = imageThumbnailLocalDataSource.getImageThumbnailPagingSource(query).getData()
        for(i in dataList.indices){
            assert(dataList[i] == sortedPagingData[i])
        }
        db.clearAllTables()
    }

    @Test
    fun getImageThumbnailPagingSourceReturnNullWhenDeleteImageThumbnailEntityFromQueryTest() = runBlocking {
        val firstData = mockFactory.createImageThumbnailEntity("1")
        val secondData = mockFactory.createImageThumbnailEntity("2")
        val thirdData = mockFactory.createImageThumbnailEntity("3")
        val dataList = listOf(thirdData,secondData,firstData)
        imageThumbnailLocalDataSource.insertThumbnailList(dataList)
        var sortedPagingData = imageThumbnailLocalDataSource.getImageThumbnailPagingSource(query).getData()
        assert(dataList.size == sortedPagingData.size)
        imageThumbnailLocalDataSource.deleteFromQuery(query)
        sortedPagingData = imageThumbnailLocalDataSource.getImageThumbnailPagingSource(query).getData()
        assert(sortedPagingData.isEmpty())
        db.clearAllTables()
    }

    @Test
    fun getImageThumbnailLibraryReturnImageThumbnailLibraryOrderByInsertTimeDESCWhenInsertImageThumbnailLibraryTest() = runBlocking {
        val firstData = mockFactory.createImageThumbnailLibraryEntity("0")
        delay(10)
        val secondData = mockFactory.createImageThumbnailLibraryEntity("1")
        val dataList = listOf(secondData,firstData)
        imageThumbnailLocalDataSource.insertImageThumbnailLibrary(secondData)
        imageThumbnailLocalDataSource.insertImageThumbnailLibrary(firstData)
        val sortedData = imageThumbnailLocalDataSource.getImageThumbnailLibrary()
        for(i in dataList.indices){
            assert(dataList[i] == sortedData[i])
        }
        db.clearAllTables()
    }

    @Test
    fun getImageThumbnailLibraryReturnEmptyListWhenDeleteImageThumbnailLibraryFromUrlTest() = runBlocking {
        val url = "0"
        val data = mockFactory.createImageThumbnailLibraryEntity(url)
        imageThumbnailLocalDataSource.insertImageThumbnailLibrary(data)
        var dataList = imageThumbnailLocalDataSource.getImageThumbnailLibrary()
        assert(dataList.isNotEmpty())
        imageThumbnailLocalDataSource.deleteImageThumbnailLibraryFromUrl(url)
        dataList = imageThumbnailLocalDataSource.getImageThumbnailLibrary()
        assert(dataList.isEmpty())
    }
}