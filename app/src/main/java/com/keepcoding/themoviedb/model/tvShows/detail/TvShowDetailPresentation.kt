package com.keepcoding.themoviedb.model.tvShows.detail

data class TvShowDetailPresentation(
    val id: Int = 0,
    val overview: String = "",
    val name: String = "",
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val posterPath: String = "",
    val voteCount: Int = 0,
    val favourite: Boolean = false
)
