package com.jydev.data.mapper

import com.example.util.dateTimeStringToTimeMil
import com.jydev.data.model.local.ImageThumbnailEntity
import com.jydev.data.model.local.ImageThumbnailLibraryEntity
import com.jydev.data.model.remote.SearchImageResponse
import com.jydev.data.model.remote.SearchVideoResponse
import com.jydev.domain.model.ImageThumbnail
import com.jydev.domain.model.ImageThumbnailLibrary

fun SearchImageResponse.toEntity(query : String) : List<ImageThumbnailEntity> =
    documents.map {
        ImageThumbnailEntity(it.thumbnailUrl, query, dateTimeStringToTimeMil(timeDate = it.datetime))
    }

fun SearchVideoResponse.toEntity(query : String) : List<ImageThumbnailEntity> =
    documents.map {
        ImageThumbnailEntity(it.thumbnail, query, dateTimeStringToTimeMil(timeDate = it.datetime))
    }

fun ImageThumbnailEntity.toDomain() : ImageThumbnail =
    ImageThumbnail(url, dateTime)

fun ImageThumbnailLibraryEntity.toDomain() : ImageThumbnailLibrary =
    ImageThumbnailLibrary(url, dateTime)

fun ImageThumbnailLibrary.toEntity() : ImageThumbnailLibraryEntity =
    ImageThumbnailLibraryEntity(url,dateTime,System.currentTimeMillis())