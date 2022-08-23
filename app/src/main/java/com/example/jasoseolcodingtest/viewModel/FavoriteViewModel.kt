package com.example.jasoseolcodingtest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jasoseolcodingtest.domain.MovieRepository
import com.example.jasoseolcodingtest.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val favorites: LiveData<List<MovieItem>> = movieRepository.getFavoriteMovie()

    fun deleteFavorite(movie: MovieItem) {
        movieRepository.deleteFavoriteMovie(movie)
    }
}