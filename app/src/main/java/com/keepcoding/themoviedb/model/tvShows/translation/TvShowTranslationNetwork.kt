package com.keepcoding.themoviedb.model.tvShows.translation

import com.squareup.moshi.Json


data class TvShowTranslationNetwork(
    @Json(name = "name") val name: String = "",
    @Json(name = "english_name") val englishName: String = "",
    @Json(name = "data") val data: TvShowTranslation
)

data class TvShowTranslation(
    @Json(name = "name") val name: String = "",
    @Json(name = "overview") val overview: String = ""
)
