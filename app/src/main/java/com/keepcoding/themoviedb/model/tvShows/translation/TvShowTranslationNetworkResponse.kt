package com.keepcoding.themoviedb.model.tvShows.translation

import com.squareup.moshi.Json

data class TvShowTranslationNetworkResponse(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "translations") val translations: List<TvShowTranslationNetwork>
)