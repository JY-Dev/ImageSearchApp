package com.jydev.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jydev.data.model.local.ImageThumbnailRemoteKeyEntity

@Dao
interface ImageThumbnailRemoteKeyDao {
    @Query("SELECT * From ImageThumbnailRemoteKey WHERE `query` = :query")
    fun getLastRemoteKey(query : String) : ImageThumbnailRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKeyEntity: ImageThumbnailRemoteKeyEntity)

    @Query("DELETE FROM ImageThumbnailRemoteKey WHERE `query` = :query")
    suspend fun deleteRemoteKeyFromQuery(query: String)

}