package com.jydev.di

import com.jydev.data.repository.ImageThumbnailRepositoryImpl
import com.jydev.domain.repository.ImageThumbnailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindImageThumbnailRepository(
        imageThumbnailRepositoryImpl : ImageThumbnailRepositoryImpl
    ) : ImageThumbnailRepository
}