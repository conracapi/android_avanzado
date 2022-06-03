package com.keepcoding.themoviedb.view.detailtvshow

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailPresentation
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TranslationTvShowViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data: MutableLiveData<TvShowTranslationPresentation> = MutableLiveData()

    fun getViewModelTranslationTvShow (tvShowId: Int) {
        viewModelScope.launch {
            val translationTvShow = withContext(Dispatchers.IO) {
                repository.getRepositoryTranslationTvShow(tvShowId)
            }
            data.postValue(translationTvShow)
        }
    }

}