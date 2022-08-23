package com.example.jasoseolcodingtest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.jasoseolcodingtest.domain.MovieRepository
import com.example.jasoseolcodingtest.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _movieDetail = MutableLiveData<MovieItem>()
    val movieDetail: LiveData<MovieItem> = _movieDetail

    fun setMovieDetail(movie: MovieItem) {
        _movieDetail.value = movie
    }

    fun favoriteClickEvent() {
        if(_movieDetail.value != null) {
            if(_movieDetail.value!!.isFavorite) {
                movieRepository.deleteFavoriteMovie(_movieDetail.value!!)
                _movieDetail.change(false)
            }else {
                movieRepository.insertFavoriteMovie(_movieDetail.value!!)
                _movieDetail.change(true)
            }
        }

    }

    private fun MutableLiveData<MovieItem>.change(isFavorite: Boolean) {
        value = value?.copy(isFavorite = isFavorite)
    }
}