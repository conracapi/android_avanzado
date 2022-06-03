package com.keepcoding.themoviedb.data

import com.keepcoding.themoviedb.model.movies.MoviePresentation
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailPresentation
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationNetwork
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationPresentation
import com.keepcoding.themoviedb.model.tvShows.TvShowPresentation
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailPresentation
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationPresentation


interface RepositoryInterface {
    suspend fun getRepositoryPopularMovies(): List<MoviePresentation>
    suspend fun getRepositoryDetailMovie(movieId: Int): MovieDetailPresentation
    suspend fun getRepositoryTranslationMovie(movieId: Int): MovieTranslationPresentation
    suspend fun addOrRemoveFavouriteMovie(movieId: Int): MovieDetailPresentation
    suspend fun getRepositoryPopularTvShows(): List<TvShowPresentation>
    suspend fun getRepositoryDetailTvShow(tvShowId: Int): TvShowDetailPresentation
    suspend fun getRepositoryTranslationTvShow(tvShowId: Int): TvShowTranslationPresentation
    suspend fun addOrRemoveFavouriteTvShow(tvShowId: Int): TvShowDetailPresentation
}