package com.jydev.data.api

import com.jydev.data.BuildConfig
import com.jydev.data.model.remote.SearchImageResponse
import com.jydev.data.model.remote.SearchVideoResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApi {
    companion object{
        const val QUERY = "query"
        const val PAGE = "page"
        const val SIZE = "size"
    }

    @Headers("Authorization: KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}")
    @GET("/v2/search/image")
    suspend fun searchImage(
        @Query(QUERY) query : String,
        @Query(PAGE) page : Int,
        @Query(SIZE) size : Int
    ) : SearchImageResponse

    @Headers("Authorization: KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}")
    @GET("/v2/search/vclip")
    suspend fun searchVideo(
        @Query(QUERY) query : String,
        @Query(PAGE) page : Int,
        @Query(SIZE) size : Int
    ) : SearchVideoResponse
}