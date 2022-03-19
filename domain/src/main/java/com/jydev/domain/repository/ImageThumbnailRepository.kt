package com.jydev.domain.repository

import androidx.paging.PagingData
import com.jydev.domain.model.ImageThumbnail
import com.jydev.domain.model.ImageThumbnailLibrary
import kotlinx.coroutines.flow.Flow

interface ImageThumbnailRepository {
    suspend fun getImageThumbnailPagingData(query : String, pageSize : Int) : Flow<PagingData<ImageThumbnail>>

    suspend fun getImageThumbnailLibrary() : List<ImageThumbnailLibrary>

    suspend fun deleteImageThumbnailLibraryFromUrl(url : String)

    suspend fun insertImageThumbnailLibrary(imageThumbnailLibrary: ImageThumbnailLibrary)
}