package com.example.android.pagefromnetworkanddatabasepractice.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class NewsRemoteKey(@PrimaryKey val label: String, val nextKey: String?)