package com.jydev.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jydev.data.model.local.ImageThumbnailLibraryEntity

@Dao
interface ImageThumbnailLibraryDao {
    @Query("SELECT * FROM ImageThumbnailLibrary ORDER BY `insertTime` DESC")
    suspend fun getImageThumbnailLibrary() : List<ImageThumbnailLibraryEntity>

    @Query("DELETE FROM ImageThumbnailLibrary WHERE `url` =:url")
    suspend fun deleteImageThumbnailLibraryFromUrl(url : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageThumbnailLibrary(imageThumbnailLibraryEntity: ImageThumbnailLibraryEntity)
}