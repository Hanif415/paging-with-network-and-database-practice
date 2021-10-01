package com.example.android.pagefromnetworkanddatabasepractice.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<NewsEntity>)

    @Query("SELECT * FROM news")
    fun newsPagingSource(): PagingSource<Int, NewsEntity>

    @Query("DELETE FROM news")
    suspend fun clearAll()

    @Query("DELETE FROM news WHERE title = :query")
    suspend fun deleteByTitle(query: String)
}