package com.keepcoding.themoviedb.model.movies.detail

import com.squareup.moshi.Json


data class MovieDetailNetwork(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "adult") val adult: Boolean = true,
    @Json(name = "original_language") val originalLanguage: String = "",
    @Json(name = "overview") val overview: String? = "",
    @Json(name = "title") val title: String = "",
    @Json(name = "vote_average") val voteAverage: Double = 0.0,
    @Json(name = "vote_count") val voteCount: Int = 0
)
