package com.keepcoding.themoviedb.view.detailmovie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TranslationMovieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data: MutableLiveData<MovieTranslationPresentation> = MutableLiveData()

    fun getViewModelTranslationMovie(movieId: Int) {
        viewModelScope.launch {
            val translationMovie = withContext(Dispatchers.IO) {
                repository.getRepositoryTranslationMovie(movieId)
            }
            data.postValue(translationMovie)
            Log.d("Resumen película ESP", "${translationMovie.overview}")
        }
    }


}


