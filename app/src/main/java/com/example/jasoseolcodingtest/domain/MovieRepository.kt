package com.example.jasoseolcodingtest.domain

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jasoseolcodingtest.domain.local.FavoriteDAO
import com.example.jasoseolcodingtest.domain.remote.Api
import com.example.jasoseolcodingtest.domain.remote.MoviePagingSource
import com.example.jasoseolcodingtest.model.BaseDataSource
import com.example.jasoseolcodingtest.model.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: Api,
    private val favoriteDAO: FavoriteDAO
): BaseDataSource() {

    @ExperimentalPagingApi
    fun getSearchResults(query: String, pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<MovieItem>> {
        val headers = mapOf(
            "Content-Type" to "application/json; charset=UTF-8",
            "X-Naver-Client-Id" to "jIeUlNCg9tVnKepZAHy8",
            "X-Naver-Client-Secret" to "jVj7x7t7qJ"
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                MoviePagingSource(api, headers, query)
            }
        ).flow
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 30, enablePlaceholders = true)
    }

    fun getFavoriteMovie(): LiveData<List<MovieItem>> {
        return favoriteDAO.getFavorite()
    }

    fun insertFavoriteMovie(movie: MovieItem) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDAO.insert(movie)
        }
    }

    fun deleteFavoriteMovie(movie: MovieItem) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDAO.delete(movie)
        }
    }
}