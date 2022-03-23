package com.jydev.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jydev.data.datasource.ImageThumbnailLocalDataSource
import com.jydev.data.datasource.ImageThumbnailLocalDataSourceImpl
import com.jydev.data.datasource.KakaoDataSource
import com.jydev.data.local.db.ImageThumbnailDataBase
import com.jydev.data.mock.FakeKakaoDataSource
import com.jydev.data.mock.ImageFeedMockFactory
import com.jydev.data.repository.ImageFeedRepositoryImpl
import com.jydev.domain.repository.ImageFeedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ImageFeedRepositoryTest {
    private lateinit var db: ImageThumbnailDataBase
    private lateinit var imageThumbnailLocalDataSource: ImageThumbnailLocalDataSource
    private lateinit var imageFeedRepository: ImageFeedRepository
    private val fakeKakaoDataSource: KakaoDataSource = FakeKakaoDataSource()
    private val imageFeedMockFactory = ImageFeedMockFactory()

    @Before
    fun createDataSource() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ImageThumbnailDataBase::class.java).build()
        imageThumbnailLocalDataSource =
            ImageThumbnailLocalDataSourceImpl(db, db.dao(), db.remoteKeyDao(), db.libraryDao())
        imageFeedRepository =
            ImageFeedRepositoryImpl(imageThumbnailLocalDataSource, fakeKakaoDataSource)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getImageFeedLibraryReturnImageFeedLibraryOrderByInsertTimeDESCWhenInsertImageFeedLibraryTest() =
        runBlocking {
            val firstData = imageFeedMockFactory.createImageFeed("0")
            delay(10)
            val secondData = imageFeedMockFactory.createImageFeed("1")
            val dataList = listOf(firstData, secondData)
            imageFeedRepository.insertImageFeedLibrary(secondData)
            imageFeedRepository.insertImageFeedLibrary(firstData)
            val sortedData = imageFeedRepository.getImageFeedLibrary()
            for (i in dataList.indices) {
                assert(dataList[i] == sortedData[i])
            }
            db.clearAllTables()
        }

    @Test
    fun getImageFeedLibraryReturnEmptyListWhenDeleteImageFeedLibraryFromUrlTest() = runBlocking {
        val url = "0"
        val data = imageFeedMockFactory.createImageFeed(url)
        imageFeedRepository.insertImageFeedLibrary(data)
        var dataList = imageFeedRepository.getImageFeedLibrary()
        assert(dataList.isNotEmpty())
        imageThumbnailLocalDataSource.deleteImageThumbnailLibraryFromUrl(url)
        dataList = imageFeedRepository.getImageFeedLibrary()
        assert(dataList.isEmpty())
    }
}