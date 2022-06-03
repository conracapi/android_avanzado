package com.keepcoding.themoviedb.data

import android.util.Log
import com.keepcoding.themoviedb.data.local.LocalDataSource
import com.keepcoding.themoviedb.data.remote.RemoteDataSource
import com.keepcoding.themoviedb.model.movies.MoviePresentation
import com.keepcoding.themoviedb.model.movies.detail.MovieDetailPresentation
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationLocal
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationNetwork
import com.keepcoding.themoviedb.model.movies.translation.MovieTranslationPresentation
import com.keepcoding.themoviedb.model.tvShows.TvShowPresentation
import com.keepcoding.themoviedb.model.tvShows.detail.TvShowDetailPresentation
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationLocal
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationNetwork
import com.keepcoding.themoviedb.model.tvShows.translation.TvShowTranslationPresentation
import javax.inject.Inject


class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val networkToLocalMapper: NetworkToLocalMapper,
    private val localToPresentationMapper: LocalToPresentationMapper,
) : RepositoryInterface {


    /*  ========================================  */
    //  ========== - -  MOVIES  - - ==========  */
    /*  ========================================  */

    // Método para obtener TODAS LAS PELÍCULAS populares
    override suspend fun getRepositoryPopularMovies(): List<MoviePresentation> {
        Log.d("REPOSITORY_GET/MOVIES", "Voy a mostrar todas las PELÍCULAS")
        val localMovies = local.getAllMovies()
        if (localMovies.isEmpty()) {
            Log.d("REPOSITORY_GET/MOVIES", "Mi BBDD local de PELÍCULAS está VACÍA")
            val remoteMovies = remote.getRemotePopularMovies()
            Log.d("REPOSITORY_GET/MOVIES", "Estoy cogiendo las películas de la llamada remota")
            remoteMovies.forEach {
                Log.d("REPOSITORY_GET/MOVIES", "Inserto la película ${it.title} en mi BBDD local")
                local.insertMovie(networkToLocalMapper.mapToLocal(it))
                Log.d("REPOSITORY_GET/MOVIES", "Película insertada")
            }
        } else {
            Log.d("REPOSITORY_GET/MOVIES", "Mi BBDD local está COMPLETA")
            Log.d("REPOSITORY_GET/MOVIES", "Voy a mostrar las películas obteniéndolas de mi BBDD local")
        }
        val newLocalMovies = local.getAllMovies()
        return newLocalMovies.map { localToPresentationMapper.mapToPresentation(it) }
    }


    // Método para obtener EL DETALLE DE LA PELÍCULA seleccionada
    override suspend fun getRepositoryDetailMovie(movieId: Int): MovieDetailPresentation {
        Log.d("REPOSITORY_GET/DETAILMO", "Voy a mostrar el detalle de la PELÍCULA seleccionada")
        val movieDetailLocal = local.getMovieDetailById(movieId)
        if (movieDetailLocal == null) {
            Log.d("REPOSITORY_GET/DETAILMO", "No tengo el detalle de la PELÍCULA en mi BBDD local")
            val remoteDetailMovie = remote.getRemoteDetailMovie(movieId)
            Log.d("REPOSITORY_GET/DETAILMO", "Estoy cogiendo el detalle de la PELÍCULA de la llamada remota")
            val localDetailMovie = networkToLocalMapper.mapToLocalDetailMovie(remoteDetailMovie)
            return localToPresentationMapper.mapToPresentationDetailMovie(localDetailMovie)
        } else {
            Log.d("REPOSITORY_GET/DETAILMO", "Tengo el detalle de la PELÍCULA en mi BBDD local")
            Log.d("REPOSITORY_GET/DETAILMO", "Voy a mostrar el detalle de la PELÍCULA obteniéndola de mi BBDD local")
            return localToPresentationMapper.mapToPresentationDetailMovie(movieDetailLocal)
        }
    }

    // Método para obtener LA TRADUCCIÓN DEL RESUMEN DE LA PELÍCULA seleccionada
    override suspend fun getRepositoryTranslationMovie(movieId: Int): MovieTranslationPresentation {
        Log.d("REPOSITORY/GTRADUC_MOV", "Voy a mostrar la traducción de la PELÍCULA seleccionada")
        val movieDetailLocal = local.getMovieDetailById(movieId)
        if (movieDetailLocal == null) {
            Log.d("REPOSITORY/GTRADUC_MOV", "No tengo la traducción de la PELÍCULA en mi BBDD local")
            val localTranslationMovie = getMovieTranslationLocal(movieId)
            Log.d("REPOSITORY/GTRADUC_MOV", "Estoy cogiendo la traducción de la PELÍCULA de la llamada remota")
            return localToPresentationMapper.mapToPresentationTranslationMovie(localTranslationMovie)
        } else {
            Log.d("REPOSITORY/GTRADUC_MOV", "Tengo la traducción de la PELÍCULA en mi BBDD local")
            Log.d("REPOSITORY/GTRADUC_MOV", "Voy a mostrar la traducción de la PELÍCULA obteniéndola de mi BBDD local")
            return localToPresentationMapper.mapToPresentationTranslationMovie(local.getMovieTranslationById(movieId))
        }
    }

    // Método para añadir EL DETALLE/TRADUCCIÓN DE LA PELÍCULA seleccionada
    override suspend fun addOrRemoveFavouriteMovie(movieId: Int): MovieDetailPresentation {
        Log.d("REPOSITORY_ADD/REMOVE", "Voy a añadir/eliminar la película de mi BBDD local")
        val checkMovieDetailLocal = local.getMovieDetailById(movieId)
        if (checkMovieDetailLocal == null) {
            Log.d("REPOSITORY_ADD/REMOVE", "El detalle de esta película no se encuentra añadido a mi BBDD local")
            val movieDetailNetwork = remote.getRemoteDetailMovie(movieId)
            Log.d("REPOSITORY_ADD/REMOVE", "Estoy cogiendo el detalle de la PELÍCULA de la llamada remota")
            val movieDetailLocal = networkToLocalMapper.mapToLocalDetailMovie(movieDetailNetwork)
            movieDetailLocal.favourite = true
            local.insertDetailMovie(movieDetailLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He añadido a mi BBDD local el detalle de la PELÍCULA")
            Log.d("REPOSITORY_ADD/REMOVE", "Estoy cogiendo la traducción de la PELÍCULA de la llamada remota")
            val movieTranslationLocal = getMovieTranslationLocal(movieId)
            local.insertTranslationMovie(movieTranslationLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He añadido a mi BBDD local la traducción de la PELÍCULA")
            return localToPresentationMapper.mapToPresentationDetailMovie(movieDetailLocal)
        } else {
            Log.d("REPOSITORY_ADD/REMOVE", "El detalle de esta PELÍCULA sí está en mi BBDD local")
            val detailLocal = local.getMovieDetailById(movieId)
            detailLocal.favourite = false
            local.deleteDetailMovie(detailLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He eliminado el detalle de esta PELÍCULA de mi BBDD local")
            val translationLocal = local.getMovieTranslationById(movieId)
            local.deleteTranslationMovie(translationLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He eliminado la traduccón de esta PELÍCULA de mi BBDD local")
            return localToPresentationMapper.mapToPresentationDetailMovie(detailLocal)
        }
    }


    // Método para obtener parte de LA TRADUCCIÓN DEL RESUMEN DE LA PELÍCULA seleccionada
    private suspend fun getMovieTranslationLocal (movieId: Int): MovieTranslationLocal {
        val movieTranslationNetworkResponse = remote.getRemoteTranslationMovie(movieId)
        lateinit var movie: MovieTranslationNetwork
        for (movieTemp in movieTranslationNetworkResponse.translations) {
            if (movieTemp.englishName == "Spanish") {
                movie = movieTemp
            }
        }
        return networkToLocalMapper.mapToLocalTranslationMovie(movieTranslationNetworkResponse.id, movie)
    }


    /*  ========================================  */
    //  ========== - -  TV SHOWS  - - ==========  */
    /*  ========================================  */

    // Método para obtener TODAS LAS SERIES populares
    override suspend fun getRepositoryPopularTvShows(): List<TvShowPresentation> {
        Log.d("REPOSITORY_GET/TVSHOWS", "Voy a mostrar todas las SERIES")
        val localTvShows = local.getAllTvShows()
        if (localTvShows.isEmpty()) {
            Log.d("REPOSITORY_GET/TVSHOWS", "Mi BBDD local de SERIES está VACÍA")
            val remoteTvShows = remote.getRemotePopularTvShows()
            Log.d("REPOSITORY_GET/TVSHOWS", "Estoy cogiendo las SERIES de la llamada remota")
            remoteTvShows.forEach {
                Log.d("REPOSITORY_GET/TVSHOWS", "Inserto la SERIE ${it.name} en mi BBDD local")
                local.insertTvShow(networkToLocalMapper.mapToLocalTvShow(it))
                Log.d("REPOSITORY_GET/TVSHOWS", "Serie insertada")
            }
        } else {
            Log.d("REPOSITORY_GET/TVSHOWS", "Mi BBDD local de SERIES está COMPLETA")
            Log.d("REPOSITORY_GET/TVSHOWS", "Voy a mostrar las SERIES obteniéndolas de mi BBDD local")
        }
        val newLocalTvShows = local.getAllTvShows()
        return newLocalTvShows.map { localToPresentationMapper.mapToPresentationTvShow(it) }
    }

    // Método para obtener EL DETALLE DE LA SERIE seleccionada
    override suspend fun getRepositoryDetailTvShow(tvShowId: Int): TvShowDetailPresentation {
        Log.d("REPOSITORY_GET/DETAILTV", "Voy a mostrar el detalle de la SERIE seleccionada")
        val tvShowDetailLocal = local.getTvShowDetailById(tvShowId)
        if (tvShowDetailLocal == null) {
            Log.d("REPOSITORY_GET/DETAILTV", "No tengo el detalle de la SERIE en mi BBDD local")
            val remoteDetailTvShow = remote.getRemoteDetailTvShow(tvShowId)
            Log.d("REPOSITORY_GET/DETAILTV", "Estoy cogiendo el detalle de la SERIE de la llamada remota")
            val localDetailTvShow = networkToLocalMapper.mapToLocalDetailTvShow(remoteDetailTvShow)
            return localToPresentationMapper.mapToPresentationDetailTvShow(localDetailTvShow)
        } else {
            Log.d("REPOSITORY_GET/DETAILTV", "Tengo el detalle de la SERIE en mi BBDD local")
            Log.d("REPOSITORY_GET/DETAILTV", "Voy a mostrar el detalle de la SERIE obteniéndola de mi BBDD local")
            return localToPresentationMapper.mapToPresentationDetailTvShow(tvShowDetailLocal)
        }
    }


    // Método para obtener LA TRADUCCIÓN DEL RESUMEN DE LA SERIE seleccionada
    override suspend fun getRepositoryTranslationTvShow(tvShowId: Int): TvShowTranslationPresentation {
        Log.d("REPOSITORY/GTRADUC_TVSH", "Voy a mostrar la traducción de la SERIE seleccionada")
        val tvShowDetailLocal = local.getTvShowDetailById(tvShowId)
        if (tvShowDetailLocal == null) {
            Log.d("REPOSITORY/GTRADUC_TVSH", "No tengo la traducción de la SERIE en mi BBDD local")
            val localTranslationTvShow = getTvShowTranslationLocal(tvShowId)
            Log.d("REPOSITORY/GTRADUC_TVSH", "Estoy cogiendo la traducción de la SERIE de la llamada remota")
            return localToPresentationMapper.mapToPresentationTranslationTvShow(localTranslationTvShow)
        } else {
            Log.d("REPOSITORY/GTRADUC_TVSH", "Tengo la traducción de la SERIE en mi BBDD local")
            Log.d("REPOSITORY/GTRADUC_TVSH", "Voy a mostrar la traducción de la SERIE obteniéndola de mi BBDD local")
            return localToPresentationMapper.mapToPresentationTranslationTvShow(local.getTvShowTranslationById(tvShowId))
        }
    }

    // Método para añadir EL DETALLE/TRADUCCIÓN DE LA SERIE seleccionada
    override suspend fun addOrRemoveFavouriteTvShow(tvShowId: Int): TvShowDetailPresentation {
        Log.d("REPOSITORY_ADD/REMOVE", "Voy a añadir/eliminar la serie de mi BBDD local")
        val checkTvShowDetailLocal = local.getTvShowDetailById(tvShowId)
        if (checkTvShowDetailLocal == null) {
            Log.d("REPOSITORY_ADD/REMOVE", "El detalle de esta serie no se encuentra añadido a mi BBDD local")
            val tvShowDetailNetwork = remote.getRemoteDetailTvShow(tvShowId)
            Log.d("REPOSITORY_ADD/REMOVE", "Estoy cogiendo el detalle de la SERIE de la llamada remota")
            val tvShowDetailLocal = networkToLocalMapper.mapToLocalDetailTvShow(tvShowDetailNetwork)
            tvShowDetailLocal.favourite = true
            local.insertDetailTvShow(tvShowDetailLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He añadido a mi BBDD local el detalle de la SERIE")
            Log.d("REPOSITORY_ADD/REMOVE", "Estoy cogiendo la traducción de la SERIE de la llamada remota")
            val tvShowTranslationLocal = getTvShowTranslationLocal(tvShowId)
            local.insertTranslationTvShow(tvShowTranslationLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He añadido a mi BBDD local la traducción de la SERIE")
            return localToPresentationMapper.mapToPresentationDetailTvShow(tvShowDetailLocal)
        } else {
            Log.d("REPOSITORY_ADD/REMOVE", "El detalle de esta SERIE sí está en mi BBDD local")
            val detailLocal = local.getTvShowDetailById(tvShowId)
            detailLocal.favourite = false
            local.deleteDetailTvShow(detailLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He eliminado el detalle de esta SERIE de mi BBDD local")
            val translationLocal = local.getTvShowTranslationById(tvShowId)
            local.deleteTranslationTvShow(translationLocal)
            Log.d("REPOSITORY_ADD/REMOVE", "He eliminado la traduccón de esta SERIE de mi BBDD local")
            return localToPresentationMapper.mapToPresentationDetailTvShow(detailLocal)
        }
    }

    // Función privada para conseguir la parte de la traducción de la serie
    private suspend fun getTvShowTranslationLocal (tvShowId: Int): TvShowTranslationLocal {
        val tvShowTranslationNetworkResponse = remote.getRemoteTranslationTvShow(tvShowId)
        lateinit var tvShow: TvShowTranslationNetwork
        for (tvShowTemp in tvShowTranslationNetworkResponse.translations) {
            if (tvShowTemp.englishName == "Spanish") {
                tvShow = tvShowTemp
            }
        }
        return networkToLocalMapper.mapToLocalTranslationTvShow(tvShowTranslationNetworkResponse.id, tvShow)
    }



}