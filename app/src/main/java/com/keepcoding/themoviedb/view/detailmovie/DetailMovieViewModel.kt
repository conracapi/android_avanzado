package com.keepcoding.themoviedb.view.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data: MutableLiveData<MovieDetailPresentation> = MutableLiveData()

    fun getViewModelDetailMovie (movieId: Int) {
        viewModelScope.launch {
            val detailMovie = withContext(Dispatchers.IO) {
                repository.getRepositoryDetailMovie(movieId)
            }
            data.postValue(detailMovie)
        }
    }

    fun addRemoveFavouriteDetail (movieId: Int) {
        viewModelScope.launch {
            val detailMovie = withContext(Dispatchers.IO) {
                repository.addOrRemoveFavouriteMovie(movieId)
            }
            data.postValue(detailMovie)
        }
    }

}