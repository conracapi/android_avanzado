package com.keepcoding.themoviedb.data.local.tvshow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationLocal


@Dao
interface TvShowTranslationDao {

    @Query("SELECT * FROM tvShowTranslation WHERE id = :id")
    fun getTvShowTranslationById(id: Int): TvShowTranslationLocal

    @Insert
    fun insertTvShowTranslation(tvShowTranslationLocal: TvShowTranslationLocal)

    @Delete
    fun deleteTvShowTranslation(tvShowTranslationLocal: TvShowTranslationLocal)

}