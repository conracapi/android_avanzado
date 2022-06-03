package com.keepcoding.themoviedb.model.movies.translation

import com.squareup.moshi.Json

data class MovieTranslationNetworkResponse(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "translations") val translations: List<MovieTranslationNetwork>
)
