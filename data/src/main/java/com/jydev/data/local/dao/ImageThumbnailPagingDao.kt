package com.jydev.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jydev.data.model.local.ImageThumbnailEntity

@Dao
interface ImageThumbnailPagingDao {
    @Query("SELECT * FROM ImageThumbnail WHERE `query` =:query ORDER BY dateTime DESC")
    fun getImageThumbnailPagingSource(query : String) : PagingSource<Int,ImageThumbnailEntity>

    @Query("DELETE FROM ImageThumbnail WHERE `query` =:query")
    suspend fun deleteFromQuery(query: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(imageThumbnailList : List<ImageThumbnailEntity>)
}