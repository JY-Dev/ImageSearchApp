package com.jydev.data.datasource

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jydev.data.model.local.ImageThumbnailEntity
import com.jydev.data.model.local.ImageThumbnailLibraryEntity
import com.jydev.data.model.local.ImageThumbnailRemoteKeyEntity

interface ImageThumbnailLocalDataSource {
    fun getLastRemoteKey(query : String) : ImageThumbnailRemoteKeyEntity?

    suspend fun insertRemoteKey(remoteKeyEntity: ImageThumbnailRemoteKeyEntity)

    suspend fun deleteRemoteKeyFromQuery(query: String)

    fun getImageThumbnailPagingSource(query : String) : PagingSource<Int, ImageThumbnailEntity>

    suspend fun deleteFromQuery(query: String)

    suspend fun insertThumbnailList(imageThumbnailList : List<ImageThumbnailEntity>)

    suspend fun getImageThumbnailLibrary() : List<ImageThumbnailLibraryEntity>

    suspend fun deleteImageThumbnailLibraryFromUrl(url : String)

    suspend fun insertImageThumbnailLibrary(imageThumbnailLibraryEntity: ImageThumbnailLibraryEntity)

    suspend fun <T> withTransaction(block : suspend () -> T) : T
}