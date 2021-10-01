package com.example.android.pagefromnetworkanddatabasepractice.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.pagefromnetworkanddatabasepractice.api.NewsApiService
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsDatabase
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsEntity
import kotlinx.coroutines.flow.Flow


class NewsRepository(private val service: NewsApiService, private val database: NewsDatabase) {

    @ExperimentalPagingApi
    fun getSearchResultStream(query: String): Flow<PagingData<NewsEntity>> {
        val newsDao = database.newsDao()
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = NewsRemoteMediator(query, database, service)
        ) {
            newsDao.newsPagingSource()
        }.flow
    }
}