package com.example.jasoseolcodingtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jasoseolcodingtest.R
import com.example.jasoseolcodingtest.databinding.ListItemMovieBinding
import com.example.jasoseolcodingtest.event.MovieClickEvent
import com.example.jasoseolcodingtest.model.Movie
import com.example.jasoseolcodingtest.model.MovieItem

class MovieAdapter(listener: MovieClickEvent): PagingDataAdapter<MovieItem, MovieAdapter.MovieViewHolder>(
    MovieDiffCallback()
) {
    private val mCallback = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bind(movieItem)
    }

    inner class MovieViewHolder(
        private val binding: ListItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                when(it.id) {
                    R.id.cl_movie -> { mCallback.goMovieDetail(binding.movieItem!!) }
                    R.id.iv_favorite -> { mCallback.addFavorite(binding.movieItem!!) }
                }
            }
        }
        fun bind(item: MovieItem?) {
            binding.apply {
                movieItem = item
                executePendingBindings()
            }
        }
    }
}

private class MovieDiffCallback: DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}