package com.example.android.pagefromnetworkanddatabasepractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.android.pagefromnetworkanddatabasepractice.database.NewsEntity
import com.example.android.pagefromnetworkanddatabasepractice.databinding.NewsViewItemBinding

class NewsAdapter(differCallback: DiffUtil.ItemCallback<NewsEntity>) :
    PagingDataAdapter<NewsEntity, NewsViewHolder>(differCallback) {
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val viewBinding =
            NewsViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(viewBinding)
    }

    object NewsComparator : DiffUtil.ItemCallback<NewsEntity>() {
        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem == newItem
        }

    }
}