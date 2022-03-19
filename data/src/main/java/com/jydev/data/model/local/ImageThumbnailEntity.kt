package com.jydev.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImageThumbnail")
data class ImageThumbnailEntity(
    @PrimaryKey val url: String,
    val query : String,
    val dateTime: Long
)