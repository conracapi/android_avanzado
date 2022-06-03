package com.keepcoding.themoviedb.model.movies.detail


data class MovieDetailPresentation(
    val id: Int = 0,
    val adult: Boolean = true,
    val originalLanguage: String = "",
    val overview: String? = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val favourite: Boolean = false
)
