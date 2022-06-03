package com.keepcoding.themoviedb.model.tvShows

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowPresentation(
    val id: Int = 0,
    val name: String = "",
    val posterPath: String = "",
    val voteCount: Int = 0,
) : Parcelable