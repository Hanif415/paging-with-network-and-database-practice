package com.example.android.pagefromnetworkanddatabasepractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class, NewsRemoteKey::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    companion object {
        fun create(
            context: Context,
            useInMemory: Boolean
        ): NewsDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(
                    context,
                    NewsDatabase::class.java
                )
            } else {
                Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java,
                    "db.db"
                )
            }
            return databaseBuilder.fallbackToDestructiveMigration().build()
        }
    }

    abstract fun newsDao(): NewsDao
    abstract fun remoteKeyDao(): NewsRemoteKeyDao
//    abstract fun lastUpdated(): Int
}