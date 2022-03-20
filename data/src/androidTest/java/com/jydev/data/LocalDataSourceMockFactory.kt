package com.jydev.data

import com.jydev.data.model.local.ImageThumbnailEntity
import com.jydev.data.model.local.ImageThumbnailLibraryEntity
import com.jydev.data.model.local.ImageThumbnailRemoteKeyEntity

class LocalDataSourceMockFactory {
    val query = "고양이"
    fun createRemoteKeyEntity(query : String = this.query , imageNextKey : Int = 1 , videoNextKey : Int = 1,insertTime : Long = System.currentTimeMillis()) : ImageThumbnailRemoteKeyEntity =
        ImageThumbnailRemoteKeyEntity(query,imageNextKey,videoNextKey,insertTime)

    fun createImageThumbnailEntity(url : String, query : String = this.query , dateTime : Long = System.currentTimeMillis()) : ImageThumbnailEntity =
        ImageThumbnailEntity(url, query,dateTime)

    fun createImageThumbnailLibraryEntity(url : String, dateTime: Long = System.currentTimeMillis(), insertTime: Long = System.currentTimeMillis()) : ImageThumbnailLibraryEntity =
        ImageThumbnailLibraryEntity(url, dateTime, insertTime)
}