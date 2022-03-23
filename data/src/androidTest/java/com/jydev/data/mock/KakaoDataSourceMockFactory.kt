package com.jydev.data.mock

import com.jydev.data.model.remote.MetaResponse
import com.jydev.data.model.remote.SearchImageResponse
import com.jydev.data.model.remote.SearchVideoResponse
import java.text.SimpleDateFormat
import java.util.*

class KakaoDataSourceMockFactory {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.KOREA)
    fun createMetaResponse(): MetaResponse =
        MetaResponse(false, 0, 0)

    private fun createSearchImageDocumentResponse(): SearchImageResponse.DocumentResponse =
        SearchImageResponse.DocumentResponse(
            "test",
            dateFormat.format(Date(System.currentTimeMillis())),
            "",
            "",
            0,
            "image/${dateFormat.format(Date(System.currentTimeMillis()))}",
            "",
            0
        )

    private fun createSearchVideoDocumentResponse(): SearchVideoResponse.DocumentResponse =
        SearchVideoResponse.DocumentResponse(
            "",
            dateFormat.format(Date(System.currentTimeMillis())),
            0,
            "video/${dateFormat.format(Date(System.currentTimeMillis()))}",
            "",
            ""
        )

    fun createSearchImageList() =
        listOf(
            createSearchImageDocumentResponse(),
            createSearchImageDocumentResponse(),
            createSearchImageDocumentResponse()
        )

    fun createSearchVideoList() =
        listOf(
            createSearchVideoDocumentResponse(),
            createSearchVideoDocumentResponse(),
            createSearchVideoDocumentResponse()
        )
}