package com.jydev.data.datasource

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.jydev.data.local.dao.ImageThumbnailPagingDao
import com.jydev.data.local.db.ImageThumbnailDataBase
import com.jydev.data.local.dao.ImageThumbnailLibraryDao
import com.jydev.data.local.dao.ImageThumbnailRemoteKeyDao
import com.jydev.data.model.local.ImageThumbnailEntity
import com.jydev.data.model.local.ImageThumbnailLibraryEntity
import com.jydev.data.model.local.ImageThumbnailRemoteKeyEntity
import javax.inject.Inject

class ImageThumbnailLocalDataSourceImpl @Inject constructor(
    private val database: ImageThumbnailDataBase,
    private val imageThumbnailPagingDao: ImageThumbnailPagingDao,
    private val imageThumbnailRemoteKeyDao: ImageThumbnailRemoteKeyDao,
    private val imageThumbnailLibraryDao: ImageThumbnailLibraryDao
) : ImageThumbnailLocalDataSource {
    override fun getLastRemoteKey(query: String): ImageThumbnailRemoteKeyEntity? =
        imageThumbnailRemoteKeyDao.getLastRemoteKey(query)

    override suspend fun insertRemoteKey(remoteKeyEntity: ImageThumbnailRemoteKeyEntity) =
        imageThumbnailRemoteKeyDao.insertRemoteKey(remoteKeyEntity)

    override suspend fun deleteRemoteKeyFromQuery(query: String) =
        imageThumbnailRemoteKeyDao.deleteRemoteKeyFromQuery(query)

    override fun getImageThumbnailPagingSource(query: String): PagingSource<Int, ImageThumbnailEntity> =
        imageThumbnailPagingDao.getImageThumbnailPagingSource(query)

    override suspend fun deleteFromQuery(query: String) =
        imageThumbnailPagingDao.deleteFromQuery(query)

    override suspend fun insertThumbnailList(imageThumbnailList: List<ImageThumbnailEntity>) =
        imageThumbnailPagingDao.insertAll(imageThumbnailList)

    override suspend fun getImageThumbnailLibrary(): List<ImageThumbnailLibraryEntity> =
        imageThumbnailLibraryDao.getImageThumbnailLibrary()

    override suspend fun deleteImageThumbnailLibraryFromUrl(url: String) =
        imageThumbnailLibraryDao.deleteImageThumbnailLibraryFromUrl(url)

    override suspend fun insertImageThumbnailLibrary(imageThumbnailLibraryEntity: ImageThumbnailLibraryEntity) =
        imageThumbnailLibraryDao.insertImageThumbnailLibrary(imageThumbnailLibraryEntity)

    override suspend fun <T> withTransaction(block: suspend () -> T): T {
        return database.withTransaction {
            block()
        }
    }
}