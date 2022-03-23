package com.jydev.data.mock

import com.jydev.data.datasource.KakaoDataSource
import com.jydev.data.model.remote.SearchImageResponse
import com.jydev.data.model.remote.SearchVideoResponse

class FakeKakaoDataSource : KakaoDataSource {
    private val mockFactory = KakaoDataSourceMockFactory()
    override suspend fun searchImage(query: String, page: Int, pageSize: Int): SearchImageResponse =
        SearchImageResponse(mockFactory.createSearchImageList(),mockFactory.createMetaResponse())

    override suspend fun searchVideo(query: String, page: Int, pageSize: Int): SearchVideoResponse =
        SearchVideoResponse(mockFactory.createSearchVideoList(),mockFactory.createMetaResponse())
}