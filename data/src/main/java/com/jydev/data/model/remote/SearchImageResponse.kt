package com.jydev.data.model.remote

import com.google.gson.annotations.SerializedName

data class SearchImageResponse(
    val documents: List<DocumentResponse>,
    val meta: MetaResponse
){
    data class DocumentResponse(
        val collection: String,
        val datetime: String,
        @SerializedName("display_sitename")
        val displaySiteName: String,
        @SerializedName("doc_url")
        val docUrl: String,
        val height: Int,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,
        val width: Int
    )
}