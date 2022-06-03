package com.keepcoding.themoviedb.data.local.tvshow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailLocal


@Dao
interface TvShowDetailDao {

    @Query("SELECT * FROM tvShowDetail WHERE id = :id")
    fun getTvShowDetailById(id: Int): TvShowDetailLocal

    @Insert
    fun insertTvShowDetail(tvShowDetailLocal: TvShowDetailLocal)

    @Delete
    fun deleteTvShowDetail(tvShowDetailLocal: TvShowDetailLocal)
}