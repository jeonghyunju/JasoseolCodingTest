package com.example.jasoseolcodingtest.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jasoseolcodingtest.model.Movie
import com.example.jasoseolcodingtest.model.MovieItem
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class MoviePagingSource(
    private val api: Api,
    private val headers: Map<String, String>,
    private val query: String,
): PagingSource<Int, MovieItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val page = params.key ?: 1
        return try {
            val response = api.getSearchMovie(headers, query, 30, page)
            val nextKey =
                if(response.body()!!.items.isEmpty()) {
                    null
                }else {
                    page + 30
                }
            LoadResult.Page(
                response.body()!!.items,
                prevKey = null,
                nextKey = nextKey
            )
        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        }catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        TODO("Not yet implemented")
    }
}