package com.keepcoding.themoviedb.model.tvShows

import com.squareup.moshi.Json

data class PopularTvShowsResponse(
    @Json(name = "total_result") val totalResult: Int?,
    @Json(name = "results") val results: List<TvShowNetwork>
)
