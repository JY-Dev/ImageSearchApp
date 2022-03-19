package com.jydev.data.model.remote

import com.google.gson.annotations.SerializedName

data class SearchVideoResponse(
    val documents: List<DocumentResponse>,
    val meta: MetaResponse
){
    data class DocumentResponse(
        val author: String,
        val datetime: String,
        @SerializedName("play_time")
        val playTime: Int,
        val thumbnail: String,
        val title: String,
        val url: String
    )
}