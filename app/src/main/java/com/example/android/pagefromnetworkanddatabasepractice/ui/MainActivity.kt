package com.example.android.pagefromnetworkanddatabasepractice.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.android.pagefromnetworkanddatabasepractice.api.NewsApiService
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsDatabase
import com.example.android.pagefromnetworkanddatabasepractice.databinding.ActivityMainBinding
import com.example.android.pagefromnetworkanddatabasepractice.repository.NewsRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<NewsViewModel> {
        ViewModelFactory(NewsRepository(NewsApiService.create(), NewsDatabase.create(this, false)))
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagingAdapter = NewsAdapter(NewsAdapter.NewsComparator)

        binding.list.adapter = pagingAdapter

        lifecycleScope.launch {
            viewModel.searchNews("Android").collectLatest { news ->
                pagingAdapter.submitData(news)
            }
        }
    }
}