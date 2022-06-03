package com.keepcoding.themoviedb.model.movies.translation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movieTranslation")
data class MovieTranslationLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int?,
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "english_name") val englishName: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "overview") val overview: String = ""
)

