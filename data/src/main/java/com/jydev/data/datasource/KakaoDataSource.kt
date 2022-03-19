package com.jydev.data.datasource

import com.jydev.data.model.remote.SearchImageResponse
import com.jydev.data.model.remote.SearchVideoResponse

interface KakaoDataSource {
    suspend fun searchImage(query : String, page : Int, pageSize : Int) : SearchImageResponse
    suspend fun searchVideo(query: String, page: Int, pageSize : Int) : SearchVideoResponse
}