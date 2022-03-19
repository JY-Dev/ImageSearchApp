package com.jydev.di

import com.jydev.data.datasource.ImageThumbnailLocalDataSource
import com.jydev.data.datasource.ImageThumbnailLocalDataSourceImpl
import com.jydev.data.datasource.KakaoDataSource
import com.jydev.data.datasource.KakaoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindKakaoDataSource(
        kakaoDataSourceImpl: KakaoDataSourceImpl
    ) : KakaoDataSource

    @Binds
    abstract fun imageThumbnailLocalDataSource(
        imageThumbnailLocalDataSourceImpl: ImageThumbnailLocalDataSourceImpl
    ) : ImageThumbnailLocalDataSource
}