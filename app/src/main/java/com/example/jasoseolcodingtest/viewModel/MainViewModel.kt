package com.example.jasoseolcodingtest.viewModel

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jasoseolcodingtest.domain.MovieRepository
import com.example.jasoseolcodingtest.model.Movie
import com.example.jasoseolcodingtest.model.MovieItem
import com.example.jasoseolcodingtest.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val searchText = MutableLiveData<String>()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    /*val editorActionListener: TextView.OnEditorActionListener =
        TextView.OnEditorActionListener { _, actionId, _ ->
            run {
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(searchText.value != null && searchText.value!!.isNotEmpty()) {
                        viewModelScope.launch(Dispatchers.IO) {
                            getSearchMovie().collectLatest { list ->
                                _movies.postValue(list)
                            }
                        }
                    }
                    true
                }else {
                    false
                }
                true
            }
        }*/

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchMovie(): Flow<PagingData<MovieItem>> {
        val response = movieRepository.getSearchResults(searchText.value!!)
        return response.cachedIn(viewModelScope)
    }
}