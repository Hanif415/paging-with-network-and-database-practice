package com.example.android.pagefromnetworkanddatabasepractice.ui

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsEntity
import com.example.android.pagefromnetworkanddatabasepractice.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    @ExperimentalPagingApi

    fun searchNews(query: String): Flow<PagingData<NewsEntity>> =
        repository.getSearchResultStream(query)

}