package com.jydev.di

import com.jydev.domain.repository.ImageFeedRepository
import com.jydev.domain.usecase.DeleteImageFeedLibraryFromUrlUseCase
import com.jydev.domain.usecase.GetImageFeedLibraryUseCase
import com.jydev.domain.usecase.GetImageFeedPagingDataUseCase
import com.jydev.domain.usecase.InsertImageFeedLibraryUseCase
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
    fun provideGetImageThumbnailPagingDataUseCase(imageFeedRepository: ImageFeedRepository) : GetImageFeedPagingDataUseCase =
        GetImageFeedPagingDataUseCase(imageFeedRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertImageThumbnailLibraryUseCase(imageFeedRepository: ImageFeedRepository) : InsertImageFeedLibraryUseCase =
        InsertImageFeedLibraryUseCase(imageFeedRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteThumbnailLibraryFromUrlUseCase(imageFeedRepository: ImageFeedRepository) : DeleteImageFeedLibraryFromUrlUseCase =
        DeleteImageFeedLibraryFromUrlUseCase(imageFeedRepository)

    @Provides
    @ViewModelScoped
    fun provideGetImageThumbnailLibraryUseCase(imageFeedRepository: ImageFeedRepository) : GetImageFeedLibraryUseCase =
        GetImageFeedLibraryUseCase(imageFeedRepository)
}