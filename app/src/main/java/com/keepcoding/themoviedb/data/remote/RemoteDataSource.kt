package com.keepcoding.themoviedb.data.remote

import android.util.Log
import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailNetwork
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationNetworkResponse
import com.keepcoding.themoviedb.model.tvShows.TvShowNetwork
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailNetwork
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationNetworkResponse
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val api: TheMovieDbAPI
): RemoteDataSourceInterface {


    /*  ==========================  */
    /*  =====  -  MOVIES  -  =====  */
    /*  ==========================  */

    override suspend fun getRemotePopularMovies(): List<MovieNetwork> {
        val response = api.getTheMovieDBPopularMovies()
        return response.results
    }

    override suspend fun getRemoteDetailMovie(movieId: Int): MovieDetailNetwork {
        Log.d("REMOTE_GET/DETAIL", "Te retorno el detalle de la película")
        return api.getTheMovieDBDetailMovie(movieId)
    }

    override suspend fun getRemoteTranslationMovie(movieId: Int): MovieTranslationNetworkResponse {
        Log.d("REMOTE_GTRANSLATION", "Te retorno la traducción de la película")
        return api.getTheMovieDBTranslationMovie(movieId)
    }


    /*  ============================  */
    /*  =====  -  TV SHOWS  -  =====  */
    /*  ============================  */

    override suspend fun getRemotePopularTvShows(): List<TvShowNetwork> {
        val response = api.getTheMovieDBPopularTvShows()
        return response.results
    }

    override suspend fun getRemoteDetailTvShow(tvShowId: Int): TvShowDetailNetwork {
        Log.d("REMOTE_GET/DETAIL", "Te retorno el detalle de la serie")
        return api.getTheMovieDBDetailTvShow(tvShowId)
    }

    override suspend fun getRemoteTranslationTvShow(tvShowId: Int): TvShowTranslationNetworkResponse {
        Log.d("REMOTE_GTRANSLATION", "Te retorno la traducción de la serie")
        return api.getTheMovieDBTranslationTvShow(tvShowId)
    }








}
