package com.jydev.data.repository

import androidx.paging.*
import com.jydev.data.datasource.ImageThumbnailLocalDataSource
import com.jydev.data.datasource.KakaoDataSource
import com.jydev.data.mapper.toDomain
import com.jydev.data.mapper.toEntity
import com.jydev.data.paging.ImageThumbnailRemoteMediator
import com.jydev.domain.model.ImageThumbnail
import com.jydev.domain.model.ImageThumbnailLibrary
import com.jydev.domain.repository.ImageThumbnailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageThumbnailRepositoryImpl @Inject constructor(
    private val imageThumbnailLocalDataSource: ImageThumbnailLocalDataSource,
    private val kakaoDataSource: KakaoDataSource
) : ImageThumbnailRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getImageThumbnailPagingData(query: String , pageSize : Int): Flow<PagingData<ImageThumbnail>> =
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

    override suspend fun getImageThumbnailLibrary(): List<ImageThumbnailLibrary> =
        imageThumbnailLocalDataSource.getImageThumbnailLibrary().map {
            it.toDomain()
        }


    override suspend fun deleteImageThumbnailLibraryFromUrl(url: String) =
        imageThumbnailLocalDataSource.withTransaction {
            imageThumbnailLocalDataSource.deleteImageThumbnailLibraryFromUrl(url)
        }

    override suspend fun insertImageThumbnailLibrary(imageThumbnailLibrary: ImageThumbnailLibrary) =
        imageThumbnailLocalDataSource.withTransaction {
            imageThumbnailLocalDataSource.insertImageThumbnailLibrary(imageThumbnailLibrary.toEntity())
        }
}