package com.jydev.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jydev.data.datasource.ImageThumbnailLocalDataSource
import com.jydev.data.datasource.KakaoDataSource
import com.jydev.data.mapper.toEntity
import com.jydev.data.model.local.ImageThumbnailEntity
import com.jydev.data.model.local.ImageThumbnailRemoteKeyEntity
import java.lang.Exception
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class ImageThumbnailRemoteMediator(
    private val query: String,
    private val imageThumbnailLocalDataSource: ImageThumbnailLocalDataSource,
    private val kakaoDataSource: KakaoDataSource,
    private val pageSize: Int
) : RemoteMediator<Int, ImageThumbnailEntity>() {
    private val cacheTimeOut = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES)
    private val limitImagePage = 50
    private val limitVideoPage = 15

    override suspend fun initialize(): InitializeAction {
        val remoteKeyTime = imageThumbnailLocalDataSource.withTransaction {
            imageThumbnailLocalDataSource.getLastRemoteKey(query)?.insertTimeDate ?: 0L
        }
        return if (System.currentTimeMillis() - remoteKeyTime >= cacheTimeOut) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else
            InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageThumbnailEntity>
    ): MediatorResult {
        return try {
            if (loadType == LoadType.PREPEND)
                return MediatorResult.Success(endOfPaginationReached = true)
            val loadImageNextKey = getImageNextKey(loadType)
            val loadVideoNextKey = getVideoNextKey(loadType)
            var insertImageNextKey = -1
            var insertVideoNextKey = -1
            val dataList: List<ImageThumbnailEntity> = mutableListOf<ImageThumbnailEntity>().apply {
                if (loadImageNextKey != -1) {
                    val data = kakaoDataSource.searchImage(query, loadImageNextKey, pageSize)
                        .toEntity(query)
                    addAll(data)
                    if (loadImageNextKey != limitImagePage && data.isNotEmpty())
                        insertImageNextKey = loadImageNextKey.plus(1)
                }
                if (loadVideoNextKey != -1) {
                    val data = kakaoDataSource.searchVideo(query, loadImageNextKey, pageSize)
                        .toEntity(query)
                    addAll(data)
                    if (loadImageNextKey != limitVideoPage && data.isNotEmpty())
                        insertVideoNextKey = loadImageNextKey.plus(1)
                }
            }
            imageThumbnailLocalDataSource.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    imageThumbnailLocalDataSource.deleteFromQuery(query)
                    imageThumbnailLocalDataSource.deleteRemoteKeyFromQuery(query)
                }
                imageThumbnailLocalDataSource.insertRemoteKey(
                    ImageThumbnailRemoteKeyEntity(
                        query,
                        insertImageNextKey,
                        insertVideoNextKey,
                        System.currentTimeMillis()
                    )
                )
                imageThumbnailLocalDataSource.insertThumbnailList(dataList)
                MediatorResult.Success(endOfPaginationReached = dataList.isEmpty())
            }
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getImageNextKey(loadType: LoadType) = when (loadType) {
        LoadType.REFRESH -> 1
        LoadType.APPEND ->
            imageThumbnailLocalDataSource.withTransaction {
                imageThumbnailLocalDataSource.getLastRemoteKey(
                    query
                )?.imageNextKey ?: 1
            }
        else -> -1
    }

    private suspend fun getVideoNextKey(loadType: LoadType) = when (loadType) {
        LoadType.REFRESH -> 1
        LoadType.APPEND ->
            imageThumbnailLocalDataSource.withTransaction {
                imageThumbnailLocalDataSource.getLastRemoteKey(
                    query
                )?.videoNextKey ?: 1
            }
        else -> -1
    }
}