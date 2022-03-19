package com.jydev.data.repository

import androidx.paging.*
import com.jydev.data.datasource.ImageThumbnailLocalDataSource
import com.jydev.data.datasource.KakaoDataSource
import com.jydev.data.mapper.toDomain
import com.jydev.data.mapper.toEntity
import com.jydev.data.paging.ImageThumbnailRemoteMediator
import com.jydev.domain.model.ImageFeed
import com.jydev.domain.repository.ImageFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageFeedRepositoryImpl @Inject constructor(
    private val imageThumbnailLocalDataSource: ImageThumbnailLocalDataSource,
    private val kakaoDataSource: KakaoDataSource
) : ImageFeedRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getImageFeedPagingData(query: String, pageSize : Int): Flow<PagingData<ImageFeed>> =
        Pager(
            PagingConfig(pageSize = pageSize),
            remoteMediator = ImageThumbnailRemoteMediator(
                query,
                imageThumbnailLocalDataSource,
                kakaoDataSource,
                pageSize
            )
        ){imageThumbnailLocalDataSource.getImageThumbnailPagingSource(query)}.flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }

    override suspend fun getImageFeedLibrary(): List<ImageFeed> =
        imageThumbnailLocalDataSource.getImageThumbnailLibrary().map {
            it.toDomain()
        }


    override suspend fun deleteImageFeedLibraryFromUrl(url: String) =
        imageThumbnailLocalDataSource.withTransaction {
            imageThumbnailLocalDataSource.deleteImageThumbnailLibraryFromUrl(url)
        }

    override suspend fun insertImageFeedLibrary(imageFeed: ImageFeed) =
        imageThumbnailLocalDataSource.withTransaction {
            imageThumbnailLocalDataSource.insertImageThumbnailLibrary(imageFeed.toEntity())
        }
}