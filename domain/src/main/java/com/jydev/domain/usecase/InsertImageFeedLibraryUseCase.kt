package com.jydev.domain.usecase

import com.jydev.domain.model.ImageFeed
import com.jydev.domain.repository.ImageFeedRepository
import javax.inject.Inject

class InsertImageFeedLibraryUseCase @Inject constructor(private val imageFeedRepository: ImageFeedRepository) {
    suspend operator fun invoke(imageFeed: ImageFeed){
        imageFeedRepository.insertImageFeedLibrary(imageFeed)
    }
}