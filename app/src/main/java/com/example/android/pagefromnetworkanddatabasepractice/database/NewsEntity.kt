package com.example.android.pagefromnetworkanddatabasepractice.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Entity(tableName = "news")
@Parcelize
data class NewsEntity(
    @PrimaryKey
    val id: Int = Random.nextInt(100),
    @Json(name = "title")
    val title: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "urlToImage")
    val urlToImage: String? = null,
) : Parcelable
