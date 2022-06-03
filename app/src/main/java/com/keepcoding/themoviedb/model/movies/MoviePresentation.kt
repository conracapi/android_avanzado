package com.keepcoding.themoviedb.model.movies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviePresentation(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val voteCount: Int = 0,
) : Parcelable
