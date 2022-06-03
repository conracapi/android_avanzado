package com.keepcoding.themoviedb.model.tvShows

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tvShows")
data class TvShowLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int?,
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "popularity") val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path") val posterPath: String = "",
    @ColumnInfo(name = "vote_count") val voteCount: Int = 0,
)
