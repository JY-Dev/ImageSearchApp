package com.jydev.domain.usecase

import androidx.annotation.IntRange
import androidx.paging.PagingData
import com.jydev.domain.model.ImageThumbnail
import com.jydev.domain.repository.ImageThumbnailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImageThumbnailPagingDataUseCase @Inject constructor(private val imageThumbnailRepository: ImageThumbnailRepository) {
    suspend operator fun invoke(query : String, @IntRange(from = 1, to = 30) pageSize : Int) : Flow<PagingData<ImageThumbnail>> {
        return imageThumbnailRepository.getImageThumbnailPagingData(query, pageSize.coerceIn(1,30))
    }
}