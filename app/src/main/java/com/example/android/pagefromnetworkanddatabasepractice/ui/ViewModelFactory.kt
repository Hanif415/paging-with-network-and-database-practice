package com.example.android.pagefromnetworkanddatabasepractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.pagefromnetworkanddatabasepractice.repository.NewsRepository

class ViewModelFactory(val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED CAST")
            return NewsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class ")
    }
}