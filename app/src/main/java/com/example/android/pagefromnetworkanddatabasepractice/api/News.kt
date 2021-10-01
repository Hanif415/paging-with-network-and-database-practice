package com.example.android.pagefromnetworkanddatabasepractice.api

import android.os.Parcelable
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsEntity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    @Json(name = "articles")
    val articles: List<NewsEntity> = emptyList(),
    val nextKey: String?

) : Parcelable

//@Entity(tableName = "news")
//@Parcelize
//data class ArticlesItem(
//    @Json(name = "title")
//    val title: String? = null,
//    @Json(name = "description")
//    val description: String? = null,
//    @Json(name = "urlToImage")
//    val urlToImage: String? = null,
//) : Parcelable
