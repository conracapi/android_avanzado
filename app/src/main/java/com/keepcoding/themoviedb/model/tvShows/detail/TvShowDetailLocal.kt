package com.keepcoding.themoviedb.model.tvShows.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "tvShowDetail")
data class TvShowDetailLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int?,
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "overview") val overview: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "number_of_episodes") val number_of_episodes: Int = 0,
    @ColumnInfo(name = "number_of_seasons") val number_of_seasons: Int = 0,
    @ColumnInfo(name = "poster_path") val posterPath: String = "",
    @ColumnInfo(name = "vote_count") val voteCount: Int = 0,
    @ColumnInfo(name = "favourite") var favourite: Boolean = false
)
