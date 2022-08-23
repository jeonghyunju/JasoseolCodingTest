package com.example.jasoseolcodingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(viewModel.searchText.value != null &&
                        viewModel.searchText.value!!.isNotEmpty()) {
                    searchMovie()
                }
            }
            true
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

    private fun setUpObserver() {

    }

    override fun goMovieDetail(movie: MovieItem) {

    }

    override fun addFavorite(movie: MovieItem) {

    }
}