package com.keepcoding.themoviedb.data.local.tvshow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.themoviedb.model.tvShows.TvShowLocal


@Dao
interface TvShowsDao {

    @Query("SELECT * FROM tvShows")
    fun getAllTvShows(): List<TvShowLocal>

    @Query("SELECT * FROM tvShows WHERE uid = :id")
    fun getTvShowById(id: Int): TvShowLocal

    @Insert
    fun insertTvShow(tvShowLocal: TvShowLocal)

    @Delete
    fun deleteTvShow(tvShowLocal: TvShowLocal)

}