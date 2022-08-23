package com.example.jasoseolcodingtest.domain.remote

import com.example.jasoseolcodingtest.model.Movie
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @GET("/v1/search/movie.json")
    suspend fun getSearchMovie(
        @HeaderMap headers: Map<String, String>,
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Response<Movie>
}