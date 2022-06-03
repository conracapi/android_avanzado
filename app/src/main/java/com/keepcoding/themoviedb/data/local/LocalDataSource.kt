package com.keepcoding.themoviedb.data.local

import com.keepcoding.themoviedb.data.local.movie.MovieDetailDao
import com.keepcoding.themoviedb.data.local.movie.MovieTranslationDao
import com.keepcoding.themoviedb.data.local.movie.MoviesDao
import com.keepcoding.themoviedb.data.local.tvshow.TvShowDetailDao
import com.keepcoding.themoviedb.data.local.tvshow.TvShowTranslationDao
import com.keepcoding.themoviedb.data.local.tvshow.TvShowsDao
import com.keepcoding.themoviedb.model.movies.MovieLocal
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailLocal
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationLocal
import com.keepcoding.themoviedb.model.tvShows.TvShowLocal
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailLocal
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationLocal
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val movieDao: MoviesDao,
    private val movieDetailDao: MovieDetailDao,
    private val movieTranslationDao: MovieTranslationDao,
    private val tvShowDao: TvShowsDao,
    private val tvShowDetailDao: TvShowDetailDao,
    private val tvShowTranslationDao: TvShowTranslationDao
) {

    // MOVIES
    fun getAllMovies(): List<MovieLocal> {  return movieDao.getAll() }
    fun insertMovie(movieLocal: MovieLocal) { movieDao.insert(movieLocal) }

    // DETAIL MOVIE
    fun getMovieDetailById(id: Int): MovieDetailLocal { return movieDetailDao.getMovieDetailById(id) }
    fun insertDetailMovie(movieDetailLocal: MovieDetailLocal) { movieDetailDao.insert(movieDetailLocal) }
    fun deleteDetailMovie(movieDetailLocal: MovieDetailLocal) { movieDetailDao.delete(movieDetailLocal)}

    // TRANSLATION MOVIE
    fun getMovieTranslationById(id: Int): MovieTranslationLocal { return movieTranslationDao.getMovieTranslationById(id) }
    fun insertTranslationMovie(movieTranslationLocal: MovieTranslationLocal) { movieTranslationDao.insert(movieTranslationLocal) }
    fun deleteTranslationMovie(movieTranslationLocal: MovieTranslationLocal) { movieTranslationDao.delete(movieTranslationLocal) }

    // TVSHOWS
    fun getAllTvShows(): List<TvShowLocal> {  return tvShowDao.getAllTvShows() }
    fun insertTvShow(tvShowLocal: TvShowLocal) { tvShowDao.insertTvShow(tvShowLocal) }

    // DETAIL TVSHOW
    fun getTvShowDetailById(id: Int): TvShowDetailLocal { return tvShowDetailDao.getTvShowDetailById(id) }
    fun insertDetailTvShow(tvShowDetailLocal: TvShowDetailLocal) { tvShowDetailDao.insertTvShowDetail(tvShowDetailLocal) }
    fun deleteDetailTvShow(tvShowDetailLocal: TvShowDetailLocal) { tvShowDetailDao.deleteTvShowDetail(tvShowDetailLocal)}

    // TRANSLATION TVSHOW
    fun getTvShowTranslationById(id: Int): TvShowTranslationLocal { return tvShowTranslationDao.getTvShowTranslationById(id) }
    fun insertTranslationTvShow(tvShowTranslationLocal: TvShowTranslationLocal) { tvShowTranslationDao.insertTvShowTranslation(tvShowTranslationLocal) }
    fun deleteTranslationTvShow(tvShowTranslationLocal: TvShowTranslationLocal) { tvShowTranslationDao.deleteTvShowTranslation(tvShowTranslationLocal) }


}
