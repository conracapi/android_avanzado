package com.keepcoding.themoviedb.data.local.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.themoviedb.model.movies.MovieLocal
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationLocal


@Dao
interface MovieTranslationDao {

    @Query("SELECT * FROM movieTranslation WHERE id = :id")
    fun getMovieTranslationById(id: Int): MovieTranslationLocal

    @Insert
    fun insert(movieTranslationLocal: MovieTranslationLocal)

    @Delete
    fun delete(movieTranslationLocal: MovieTranslationLocal)

}