package com.example.jasoseolcodingtest.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jasoseolcodingtest.model.BaseDataSource
import com.example.jasoseolcodingtest.model.Movie
import com.example.jasoseolcodingtest.model.MovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: Api
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
}