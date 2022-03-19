package com.jydev.domain.usecase

import com.jydev.domain.repository.ImageThumbnailRepository
import javax.inject.Inject

class DeleteThumbnailLibraryFromUrlUseCase @Inject constructor(private val imageThumbnailRepository: ImageThumbnailRepository) {
    suspend operator fun invoke(url : String){
        imageThumbnailRepository.deleteImageThumbnailLibraryFromUrl(url)
    }
}