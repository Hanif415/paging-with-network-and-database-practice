package com.example.android.pagefromnetworkanddatabasepractice.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.android.pagefromnetworkanddatabasepractice.api.NewsApiService
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsDatabase
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsEntity
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsRemoteKey
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class NewsRemoteMediator(
    private val query: String,
    private val database: NewsDatabase,
    private val networkService: NewsApiService
) : RemoteMediator<Int, NewsEntity>() {

    private val newsDao = database.newsDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKeyByQuery(query)
                    }

                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextKey
                }
            }

            val response = networkService.getNews(query)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery(query)
                    newsDao.deleteByTitle(query)
                }

                remoteKeyDao.insertOrReplace(NewsRemoteKey(query, response.nextKey))

                newsDao.insertAll(response.articles)
            }

            MediatorResult.Success(endOfPaginationReached = response.nextKey == null)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}