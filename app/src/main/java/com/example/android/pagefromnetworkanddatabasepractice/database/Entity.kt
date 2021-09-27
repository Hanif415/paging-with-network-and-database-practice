package com.example.android.pagefromnetworkanddatabasepractice.database

import androidx.room.Entity

@Entity(tableName = "news")
data class Entity(val id: String, val title: String, val description: String)