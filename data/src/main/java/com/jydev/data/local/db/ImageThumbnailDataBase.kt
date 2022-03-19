package com.jydev.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jydev.data.local.dao.ImageThumbnailPagingDao
import com.jydev.data.local.dao.ImageThumbnailRemoteKeyDao
import com.jydev.data.model.local.ImageThumbnailEntity
import com.jydev.data.model.local.ImageThumbnailRemoteKeyEntity

@Database(entities = [ImageThumbnailRemoteKeyEntity::class,ImageThumbnailEntity::class],version = 1)
abstract class ImageThumbnailDataBase : RoomDatabase(){
    abstract fun remoteKeyDao() : ImageThumbnailRemoteKeyDao
    abstract fun dao() : ImageThumbnailPagingDao
}