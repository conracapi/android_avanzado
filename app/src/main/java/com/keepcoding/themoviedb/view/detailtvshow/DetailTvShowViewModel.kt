package com.keepcoding.themoviedb.view.detailtvshow

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailPresentation
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailTvShowViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data: MutableLiveData<TvShowDetailPresentation> = MutableLiveData()

    fun getViewModelDetailTvShow (tvShowId: Int) {
        viewModelScope.launch {
            val detailTvShow = withContext(Dispatchers.IO) {
                repository.getRepositoryDetailTvShow(tvShowId)
            }
            data.postValue(detailTvShow)
        }
    }

    fun addOrRemoveFavouriteDetail (tvShowId: Int) {
        viewModelScope.launch {
            val detailTvShow = withContext(Dispatchers.IO) {
                repository.addOrRemoveFavouriteTvShow(tvShowId)
            }
            data.postValue(detailTvShow)
        }
    }



}