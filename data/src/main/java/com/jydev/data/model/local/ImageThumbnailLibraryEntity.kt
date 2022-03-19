package com.jydev.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImageThumbnailLibrary")
data class ImageThumbnailLibraryEntity(
    @PrimaryKey val url: String,
    val dateTime: Long,
    val insertTime : Long
)