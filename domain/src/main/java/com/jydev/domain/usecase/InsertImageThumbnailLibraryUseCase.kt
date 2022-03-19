package com.jydev.domain.usecase

import com.jydev.domain.model.ImageThumbnailLibrary
import com.jydev.domain.repository.ImageThumbnailRepository
import javax.inject.Inject

class InsertImageThumbnailLibraryUseCase @Inject constructor(private val imageThumbnailRepository: ImageThumbnailRepository) {
    suspend operator fun invoke(imageThumbnailLibrary: ImageThumbnailLibrary){
        imageThumbnailRepository.insertImageThumbnailLibrary(imageThumbnailLibrary)
    }
}