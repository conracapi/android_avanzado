package com.keepcoding.themoviedb.model.tvShows

import com.squareup.moshi.Json

data class TvShowNetwork(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "popularity") val popularity: Double = 0.0,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "vote_count") val voteCount: Int = 0,
)

