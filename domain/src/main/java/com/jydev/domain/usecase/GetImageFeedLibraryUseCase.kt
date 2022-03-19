package com.jydev.domain.usecase

import com.jydev.domain.repository.ImageFeedRepository
import javax.inject.Inject

class GetImageFeedLibraryUseCase @Inject constructor(private val imageFeedRepository: ImageFeedRepository) {
    suspend operator fun invoke() =
        imageFeedRepository.getImageFeedLibrary()
}