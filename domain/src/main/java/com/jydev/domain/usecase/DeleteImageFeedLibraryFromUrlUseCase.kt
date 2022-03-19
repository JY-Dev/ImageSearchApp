package com.jydev.domain.usecase

import com.jydev.domain.repository.ImageFeedRepository
import javax.inject.Inject

class DeleteImageFeedLibraryFromUrlUseCase @Inject constructor(private val imageFeedRepository: ImageFeedRepository) {
    suspend operator fun invoke(url : String){
        imageFeedRepository.deleteImageFeedLibraryFromUrl(url)
    }
}