package com.keepcoding.themoviedb.data.local.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailLocal


@Dao
interface MovieDetailDao {

    @Query("SELECT * FROM movieDetail WHERE id = :id")
    fun getMovieDetailById(id: Int): MovieDetailLocal

    @Insert
    fun insert(movieDetailLocal: MovieDetailLocal)

    @Delete
    fun delete(movieDetailLocal: MovieDetailLocal)

}