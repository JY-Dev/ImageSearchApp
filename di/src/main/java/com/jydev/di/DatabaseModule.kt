package com.jydev.di

import android.content.Context
import androidx.room.Room
import com.jydev.data.local.dao.ImageThumbnailPagingDao
import com.jydev.data.local.db.ImageThumbnailDataBase
import com.jydev.data.local.dao.ImageThumbnailRemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): ImageThumbnailDataBase = Room
        .databaseBuilder(context, ImageThumbnailDataBase::class.java, "image_thumbnail.db")
        .build()

    @Singleton
    @Provides
    fun provideImageThumbnailRemoteKeyDao(appDatabase: ImageThumbnailDataBase): ImageThumbnailRemoteKeyDao = appDatabase.remoteKeyDao()

    @Singleton
    @Provides
    fun provideImageThumbnailDao(appDatabase: ImageThumbnailDataBase): ImageThumbnailPagingDao = appDatabase.dao()
}