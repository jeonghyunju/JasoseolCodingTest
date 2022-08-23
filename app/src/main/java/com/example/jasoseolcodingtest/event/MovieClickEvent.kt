package com.example.jasoseolcodingtest.event

import com.example.jasoseolcodingtest.model.MovieItem

interface MovieClickEvent {
    fun goMovieDetail(movie: MovieItem)
    fun favoriteClick(movie: MovieItem)
}