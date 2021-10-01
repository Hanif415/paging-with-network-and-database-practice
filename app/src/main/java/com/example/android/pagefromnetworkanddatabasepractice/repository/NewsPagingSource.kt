package com.example.android.pagefromnetworkanddatabasepractice.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.pagefromnetworkanddatabasepractice.api.NewsApiService
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsEntity
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(private val service: NewsApiService, private val query: String) :
    PagingSource<Int, NewsEntity>() {

    override fun getRefreshKey(state: PagingState<Int, NewsEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsEntity> {
        val position = params.key ?: 1

        return try {
            val response = service.getNews(query)
            val articles = response.articles
            val nextKey = if (articles.isEmpty()) {
                null
            } else {
                position + (params.loadSize + 1)
            }

            LoadResult.Page(
                data = articles,
                prevKey = if (position == 1) null else -1,
                nextKey = nextKey
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}