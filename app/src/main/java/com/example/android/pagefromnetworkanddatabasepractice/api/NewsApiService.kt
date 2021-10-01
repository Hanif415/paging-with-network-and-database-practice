package com.example.android.pagefromnetworkanddatabasepractice.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "android",
        @Query("from") from: String = "2021-09-24",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = API_KEY
    ): News

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "09f0da02db16499a90e3c6b452b9a49d"

        fun create(): NewsApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }
}