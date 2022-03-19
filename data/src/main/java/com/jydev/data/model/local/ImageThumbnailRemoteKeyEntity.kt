package com.jydev.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImageThumbnailRemoteKey")
data class ImageThumbnailRemoteKeyEntity(@PrimaryKey val query : String, val imageNextKey : Int, val videoNextKey : Int, val insertTimeDate : Long)