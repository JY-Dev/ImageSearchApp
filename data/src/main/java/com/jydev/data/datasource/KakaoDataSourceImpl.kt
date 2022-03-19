package com.jydev.data.datasource

import com.jydev.data.api.KakaoApi
import com.jydev.data.model.remote.SearchImageResponse
import com.jydev.data.model.remote.SearchVideoResponse
import javax.inject.Inject

class KakaoDataSourceImpl @Inject constructor(private val kakaoApi: KakaoApi) : KakaoDataSource {
    override suspend fun searchImage(query: String, page: Int, pageSize :Int): SearchImageResponse =
        kakaoApi.searchImage(query, page,pageSize)

    override suspend fun searchVideo(query: String, page: Int, pageSize :Int): SearchVideoResponse =
        kakaoApi.searchVideo(query,page,pageSize)
}