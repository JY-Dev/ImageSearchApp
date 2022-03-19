package com.jydev.domain.usecase

import androidx.annotation.IntRange
import androidx.paging.PagingData
import com.jydev.domain.model.ImageFeed
import com.jydev.domain.repository.ImageFeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImageFeedPagingDataUseCase @Inject constructor(private val imageFeedRepository: ImageFeedRepository) {
    suspend operator fun invoke(query : String, @IntRange(from = 1, to = 30) pageSize : Int) : Flow<PagingData<ImageFeed>> {
        return imageFeedRepository.getImageFeedPagingData(query, pageSize.coerceIn(1,30))
    }
}