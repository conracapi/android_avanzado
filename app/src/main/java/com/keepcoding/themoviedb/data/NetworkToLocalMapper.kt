package com.keepcoding.themoviedb.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.movies.MovieLocal
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailLocal
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailNetwork
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationLocal
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationNetwork
import com.keepcoding.themoviedb.model.tvShows.TvShowLocal
import com.keepcoding.themoviedb.model.tvShows.TvShowNetwork
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailLocal
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailNetwork
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationLocal
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationNetwork
import javax.inject.Inject


class NetworkToLocalMapper @Inject constructor() {

    fun mapToLocal(movieNetwork: MovieNetwork): MovieLocal {
        return MovieLocal(
            uid = null,
            id = movieNetwork.id,
            title = movieNetwork.title,
            popularity = movieNetwork.popularity,
            posterPath = movieNetwork.posterPath,
            voteCount = movieNetwork.voteCount
        )
    }

    fun mapToLocalDetailMovie(movieDetailNetwork: MovieDetailNetwork): MovieDetailLocal {
        return MovieDetailLocal(
            uid = null,
            id = movieDetailNetwork.id,
            adult = movieDetailNetwork.adult,
            originalLanguage = movieDetailNetwork.originalLanguage,
            overview = movieDetailNetwork.overview,
            title = movieDetailNetwork.title,
            voteAverage = movieDetailNetwork.voteAverage,
            voteCount = movieDetailNetwork.voteCount
        )
    }

    fun mapToLocalTranslationMovie(idMovie: Int, movieTranslationNetwork: MovieTranslationNetwork): MovieTranslationLocal {
        return MovieTranslationLocal(
            uid = null,
            id = idMovie,
            name = movieTranslationNetwork.name,
            englishName = movieTranslationNetwork.englishName,
            title = movieTranslationNetwork.data.title,
            overview = movieTranslationNetwork.data.overview
        )
    }

    // =========

    fun mapToLocalTvShow(tvShowNetwork: TvShowNetwork): TvShowLocal {
        return TvShowLocal(
            uid = null,
            id = tvShowNetwork.id,
            name = tvShowNetwork.name,
            popularity = tvShowNetwork.popularity,
            posterPath = tvShowNetwork.posterPath,
            voteCount = tvShowNetwork.voteCount
        )
    }


    fun mapToLocalDetailTvShow(tvShowDetailNetwork: TvShowDetailNetwork): TvShowDetailLocal {
        return TvShowDetailLocal(
            uid = null,
            id = tvShowDetailNetwork.id,
            overview = tvShowDetailNetwork.overview,
            name = tvShowDetailNetwork.name,
            number_of_episodes = tvShowDetailNetwork.number_of_episodes,
            number_of_seasons = tvShowDetailNetwork.number_of_seasons,
            posterPath = tvShowDetailNetwork.posterPath,
            voteCount = tvShowDetailNetwork.voteCount,
        )
    }


    fun mapToLocalTranslationTvShow(idTvShow: Int, tvShowTranslationNetwork: TvShowTranslationNetwork): TvShowTranslationLocal {
        return TvShowTranslationLocal(
            uid = null,
            id = idTvShow,
            name = tvShowTranslationNetwork.name,
            englishName = tvShowTranslationNetwork.englishName,
            title = tvShowTranslationNetwork.data.name,
            overview = tvShowTranslationNetwork.data.overview
        )
    }

}