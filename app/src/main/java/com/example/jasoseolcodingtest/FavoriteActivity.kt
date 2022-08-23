package com.example.jasoseolcodingtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.jasoseolcodingtest.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.databinding.DataBindingUtil.setContentView
import com.example.jasoseolcodingtest.adapter.FavoriteAdapter
import com.example.jasoseolcodingtest.event.MovieClickEvent
import com.example.jasoseolcodingtest.model.MovieItem
import com.example.jasoseolcodingtest.viewModel.FavoriteViewModel

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), MovieClickEvent {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private val favoriteAdapter = FavoriteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_favorite)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvFavorite.adapter = favoriteAdapter

        setClickListener()
        setUpObserver()
    }
    private fun setClickListener() {
        binding.setClickListener {
            finish()
        }
    }

    private fun setUpObserver() {
        viewModel.favorites.observe(this) {
            favoriteAdapter.submitList(it)
        }
    }

    override fun goMovieDetail(movie: MovieItem) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    override fun favoriteClick(movie: MovieItem) {
        viewModel.deleteFavorite(movie)
    }
}