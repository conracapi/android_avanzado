package com.keepcoding.themoviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
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


@Database(entities = [MovieLocal::class, MovieDetailLocal::class, MovieTranslationLocal::class, TvShowLocal::class, TvShowDetailLocal::class, TvShowTranslationLocal::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun movieTranslationDao(): MovieTranslationDao
    abstract fun tvShowDao(): TvShowsDao
    abstract fun tvShowDetailDao(): TvShowDetailDao
    abstract fun tvShowTranslationDao(): TvShowTranslationDao
}