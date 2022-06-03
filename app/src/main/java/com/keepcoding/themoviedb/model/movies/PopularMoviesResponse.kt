package com.keepcoding.themoviedb.model.movies

import com.keepcoding.themoviedb.model.MovieNetwork
import com.squareup.moshi.Json

data class PopularMoviesResponse(
    @Json(name = "total_result") val totalResult: Int?,
    @Json(name = "results") val results: List<MovieNetwork>
)
