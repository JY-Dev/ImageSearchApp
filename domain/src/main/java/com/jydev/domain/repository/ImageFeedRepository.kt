package com.jydev.domain.repository

import androidx.paging.PagingData
import com.jydev.domain.model.ImageFeed
import kotlinx.coroutines.flow.Flow

interface ImageFeedRepository {
    suspend fun getImageFeedPagingData(query : String, pageSize : Int) : Flow<PagingData<ImageFeed>>

    suspend fun getImageFeedLibrary() : List<ImageFeed>

    suspend fun deleteImageFeedLibraryFromUrl(url : String)

    suspend fun insertImageFeedLibrary(imageFeed: ImageFeed)
}