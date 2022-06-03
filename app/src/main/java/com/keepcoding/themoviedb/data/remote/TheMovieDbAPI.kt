package com.keepcoding.themoviedb.data.remote

import com.keepcoding.themoviedb.model.movies.PopularMoviesResponse
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailNetwork
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationNetworkResponse
import com.keepcoding.themoviedb.model.tvShows.PopularTvShowsResponse
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailNetwork
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationNetwork
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbAPI {

    // =.=.= MOVIES =.=.=
    @GET("/3/movie/popular")
    suspend fun getTheMovieDBPopularMovies(): PopularMoviesResponse
    @GET("/3/movie/{movie_id}")
    suspend fun getTheMovieDBDetailMovie(@Path("movie_id") movieId: Int): MovieDetailNetwork
    @GET("/3/movie/{movie_id}/translations")
    suspend fun getTheMovieDBTranslationMovie(@Path("movie_id") movieId: Int): MovieTranslationNetworkResponse

    // =.=.= TV SHOWS =.=.=
    @GET("/3/tv/popular")
    suspend fun getTheMovieDBPopularTvShows(): PopularTvShowsResponse
    @GET("/3/tv/{tv_id}")
    suspend fun getTheMovieDBDetailTvShow(@Path("tv_id") tvShowId: Int): TvShowDetailNetwork
    @GET("/3/tv/{tv_id}/translations")
    suspend fun getTheMovieDBTranslationTvShow(@Path("tv_id") tvShowId: Int): TvShowTranslationNetworkResponse

}

