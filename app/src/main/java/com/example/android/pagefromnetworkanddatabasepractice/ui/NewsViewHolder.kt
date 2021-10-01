package com.example.android.pagefromnetworkanddatabasepractice.ui

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsEntity
import com.example.android.pagefromnetworkanddatabasepractice.databinding.NewsViewItemBinding

class NewsViewHolder(private val binding: NewsViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NewsEntity) {
        binding.newsTitle.text = item.title.toString()
        binding.newsDescription.text = item.description.toString()
        binding.imageNews.load(item.urlToImage)
    }
}
