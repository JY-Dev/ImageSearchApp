package com.jydev.di

import com.jydev.data.repository.ImageFeedRepositoryImpl
import com.jydev.domain.repository.ImageFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindImageThumbnailRepository(
        imageThumbnailRepositoryImpl : ImageFeedRepositoryImpl
    ) : ImageFeedRepository
}