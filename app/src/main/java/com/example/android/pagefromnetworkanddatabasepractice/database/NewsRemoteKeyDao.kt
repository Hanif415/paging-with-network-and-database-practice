package com.example.android.pagefromnetworkanddatabasepractice.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: NewsRemoteKey)

    @Query("SELECT * FROM remote_key WHERE label = :query")
    suspend fun remoteKeyByQuery(query: String): NewsRemoteKey

    @Query("DELETE FROM remote_key WHERE label = :query")
    suspend fun deleteByQuery(query: String)
}