package com.keepcoding.themoviedb.data

import com.keepcoding.themoviedb.model.movies.MovieLocal
import com.keepcoding.themoviedb.model.movies.MoviePresentation
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailLocal
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailPresentation
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationLocal
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationPresentation
import com.keepcoding.themoviedb.model.tvShows.TvShowLocal
import com.keepcoding.themoviedb.model.tvShows.TvShowPresentation
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailLocal
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailPresentation
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationLocal
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationPresentation
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {

    fun mapToPresentation(movieLocal: MovieLocal): MoviePresentation {
        return MoviePresentation(
            id = movieLocal.id,
            title = movieLocal.title,
            posterPath = movieLocal.posterPath,
            voteCount = movieLocal.voteCount
        )
    }

    fun mapToPresentationDetailMovie(movieDetailLocal: MovieDetailLocal): MovieDetailPresentation {
        return MovieDetailPresentation(
            id = movieDetailLocal.id,
            adult = movieDetailLocal.adult,
            originalLanguage = movieDetailLocal.originalLanguage,
            overview = movieDetailLocal.overview,
            title = movieDetailLocal.title,
            voteAverage = movieDetailLocal.voteAverage,
            voteCount = movieDetailLocal.voteCount,
            favourite = movieDetailLocal.favourite
        )
    }

    fun mapToPresentationTranslationMovie(movieTranslationLocal: MovieTranslationLocal): MovieTranslationPresentation {
        return MovieTranslationPresentation(
            id = movieTranslationLocal.id,
            name = movieTranslationLocal.name,
            englishName = movieTranslationLocal.englishName,
            title = movieTranslationLocal.title,
            overview = movieTranslationLocal.overview
        )
    }

    // =======

    fun mapToPresentationTvShow(tvShowLocal: TvShowLocal): TvShowPresentation {
        return TvShowPresentation(
            id = tvShowLocal.id,
            name = tvShowLocal.name,
            posterPath = tvShowLocal.posterPath,
            voteCount = tvShowLocal.voteCount
        )
    }

    fun mapToPresentationDetailTvShow(tvShowDetailLocal: TvShowDetailLocal): TvShowDetailPresentation {
        return TvShowDetailPresentation(
            id = tvShowDetailLocal.id,
            name = tvShowDetailLocal.name,
            overview = tvShowDetailLocal.overview,
            number_of_episodes = tvShowDetailLocal.number_of_episodes,
            number_of_seasons = tvShowDetailLocal.number_of_seasons,
            posterPath = tvShowDetailLocal.posterPath,
            voteCount = tvShowDetailLocal.voteCount,
            favourite = tvShowDetailLocal.favourite
        )
    }

    fun mapToPresentationTranslationTvShow(tvShowTranslationLocal: TvShowTranslationLocal): TvShowTranslationPresentation {
        return TvShowTranslationPresentation(
            id = tvShowTranslationLocal.id,
            name = tvShowTranslationLocal.name,
            englishName = tvShowTranslationLocal.englishName,
            title = tvShowTranslationLocal.title,
            overview = tvShowTranslationLocal.overview
        )
    }

}