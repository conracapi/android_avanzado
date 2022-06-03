package com.keepcoding.themoviedb.model.movies.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movieDetail")
data class MovieDetailLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int?,
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "adult") val adult: Boolean = true,
    @ColumnInfo(name = "original_language") val originalLanguage: String = "",
    @ColumnInfo(name = "overview") val overview: String? = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "vote_average") val voteAverage: Double = 0.0,
    @ColumnInfo(name = "vote_count") val voteCount: Int = 0,
    @ColumnInfo(name = "favourite") var favourite: Boolean = false
)
