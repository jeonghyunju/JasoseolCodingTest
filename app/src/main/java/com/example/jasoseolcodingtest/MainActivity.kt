package com.example.jasoseolcodingtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import com.example.jasoseolcodingtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.lifecycleScope
import com.example.jasoseolcodingtest.adapter.MovieAdapter
import com.example.jasoseolcodingtest.event.MovieClickEvent
import com.example.jasoseolcodingtest.model.Movie
import com.example.jasoseolcodingtest.model.MovieItem
import com.example.jasoseolcodingtest.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieClickEvent {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val movieAdapter = MovieAdapter(this)

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvMovie.adapter = movieAdapter

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(viewModel.searchText.value != null &&
                        viewModel.searchText.value!!.isNotEmpty()) {
                    searchMovie()
                }
            }
            true
        }
        setClickListener()
    }
    private fun setClickListener() {
        binding.setClickListener {
            goFavoriteList()
        }
    }

    private fun searchMovie() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getSearchMovie().collectLatest { list ->
                movieAdapter.submitData(list)
            }
        }
    }

    private fun goFavoriteList() {
        val intent = Intent(this, FavoriteActivity::class.java)
        startActivity(intent)
    }

    override fun goMovieDetail(movie: MovieItem) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    override fun favoriteClick(movie: MovieItem) {
        viewModel.favoriteClickEvent(movie)
    }
}