package com.keepcoding.themoviedb.data.remote

import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailNetwork
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationNetwork
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationNetworkResponse
import com.keepcoding.themoviedb.model.tvShows.TvShowNetwork
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailNetwork
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationNetworkResponse

interface RemoteDataSourceInterface {
    // =.=.= MOVIES =.=.=
    suspend fun getRemotePopularMovies(): List<MovieNetwork>
    suspend fun getRemoteDetailMovie(movieId: Int): MovieDetailNetwork
    suspend fun getRemoteTranslationMovie(movieId: Int): MovieTranslationNetworkResponse
    // =.=.= TV SHOWS =.=.=
    suspend fun getRemotePopularTvShows(): List<TvShowNetwork>
    suspend fun getRemoteDetailTvShow(tvShowId: Int): TvShowDetailNetwork
    suspend fun getRemoteTranslationTvShow(tvShowId: Int): TvShowTranslationNetworkResponse
}