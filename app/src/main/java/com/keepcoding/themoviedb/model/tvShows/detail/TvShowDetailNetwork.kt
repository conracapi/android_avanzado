package com.keepcoding.themoviedb.model.tvShows.detail

import com.squareup.moshi.Json


data class TvShowDetailNetwork(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "overview") val overview: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "number_of_episodes") val number_of_episodes: Int = 0,
    @Json(name = "number_of_seasons") val number_of_seasons: Int = 0,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "vote_count") val voteCount: Int = 0
)