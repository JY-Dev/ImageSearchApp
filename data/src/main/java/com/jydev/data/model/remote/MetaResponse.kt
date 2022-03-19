package com.jydev.data.model.remote

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("is_end")
    val isEnd: Boolean,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("total_count")
    val totalCount: Int
)