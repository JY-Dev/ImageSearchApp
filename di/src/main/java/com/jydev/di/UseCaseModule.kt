package com.jydev.di

import com.jydev.domain.repository.ImageThumbnailRepository
import com.jydev.domain.usecase.DeleteThumbnailLibraryFromUrlUseCase
import com.jydev.domain.usecase.GetImageThumbnailLibraryUseCase
import com.jydev.domain.usecase.GetImageThumbnailPagingDataUseCase
import com.jydev.domain.usecase.InsertImageThumbnailLibraryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetImageThumbnailPagingDataUseCase(imageThumbnailRepository: ImageThumbnailRepository) : GetImageThumbnailPagingDataUseCase =
        GetImageThumbnailPagingDataUseCase(imageThumbnailRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertImageThumbnailLibraryUseCase(imageThumbnailRepository: ImageThumbnailRepository) : InsertImageThumbnailLibraryUseCase =
        InsertImageThumbnailLibraryUseCase(imageThumbnailRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteThumbnailLibraryFromUrlUseCase(imageThumbnailRepository: ImageThumbnailRepository) : DeleteThumbnailLibraryFromUrlUseCase =
        DeleteThumbnailLibraryFromUrlUseCase(imageThumbnailRepository)

    @Provides
    @ViewModelScoped
    fun provideGetImageThumbnailLibraryUseCase(imageThumbnailRepository: ImageThumbnailRepository) : GetImageThumbnailLibraryUseCase =
        GetImageThumbnailLibraryUseCase(imageThumbnailRepository)
}