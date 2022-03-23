package com.jydev.data.mock

import com.jydev.data.mapper.toDomain
import com.jydev.data.model.local.ImageThumbnailEntity
import com.jydev.data.model.local.ImageThumbnailLibraryEntity
import com.jydev.data.model.local.ImageThumbnailRemoteKeyEntity
import com.jydev.domain.model.ImageFeed

class ImageFeedMockFactory {
    private val localDataSourceMockFactory = LocalDataSourceMockFactory()
    fun createImageFeed(url : String) : ImageFeed =
        localDataSourceMockFactory.createImageThumbnailLibraryEntity(url).toDomain()
}