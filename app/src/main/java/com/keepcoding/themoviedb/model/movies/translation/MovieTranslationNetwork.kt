package com.keepcoding.themoviedb.model.movies.translation

import com.squareup.moshi.Json


data class MovieTranslationNetwork(
    @Json(name = "name") val name: String = "",
    @Json(name = "english_name") val englishName: String = "",
    @Json(name = "data") val data: MovieTranslation
)


data class MovieTranslation(
    @Json(name = "title") val title: String = "",
    @Json(name = "overview") val overview: String = ""
)
