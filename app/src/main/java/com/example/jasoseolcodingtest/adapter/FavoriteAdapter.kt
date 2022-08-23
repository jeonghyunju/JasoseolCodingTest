package com.example.jasoseolcodingtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jasoseolcodingtest.R
import com.example.jasoseolcodingtest.databinding.ListItemMovieBinding
import com.example.jasoseolcodingtest.event.MovieClickEvent
import com.example.jasoseolcodingtest.model.MovieItem

class FavoriteAdapter(listener: MovieClickEvent): ListAdapter<MovieItem, FavoriteAdapter.FavoriteViewHolder>(
    FavoriteDiffCallback()
) {
    private val mCallback = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bind(movieItem)
    }

    inner class FavoriteViewHolder(
        private val binding: ListItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                when(it.id) {
                    R.id.cl_movie -> { mCallback.goMovieDetail(binding.movieItem!!) }
                    R.id.iv_favorite -> { mCallback.favoriteClick(binding.movieItem!!) }
                }
            }
        }

        fun bind(item: MovieItem) {
            binding.apply {
                movieItem = item
                executePendingBindings()
            }
        }
    }
}

private class FavoriteDiffCallback: DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}