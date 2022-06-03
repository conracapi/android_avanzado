package com.keepcoding.themoviedb.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.keepcoding.themoviedb.R
import com.keepcoding.themoviedb.databinding.ActivityDetailBinding
import com.keepcoding.themoviedb.model.movies.MoviePresentation
import com.keepcoding.themoviedb.model.tvShows.TvShowPresentation
import com.keepcoding.themoviedb.view.detailmovie.DetailMovieViewModel
import com.keepcoding.themoviedb.view.detailmovie.TranslationMovieViewModel
import com.keepcoding.themoviedb.view.detailtvshow.DetailTvShowViewModel
import com.keepcoding.themoviedb.view.detailtvshow.TranslationTvShowViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    // ViewBinding con los elementos de la interfaz
    private lateinit var viewBinding: ActivityDetailBinding
    // ViewModels
    private val viewModelMovieDetail: DetailMovieViewModel by viewModels()
    private val viewModelMovieTranslation: TranslationMovieViewModel by viewModels()
    private val viewModelTvShowDetail: DetailTvShowViewModel by viewModels()
    private val viewModelTvShowTranslation: TranslationTvShowViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Se pinta el back button para volver al recyclerView
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ViewBinding para coger los elementos de la interfaz
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Se actualizan la información cuando viene desde la pantalla de película
        viewModelMovieDetail.data.observe(this) {
            viewBinding.movieVoteCount.text = "Número total de votos   -   ${it.voteCount}"
            viewBinding.movieVoteAverage.text = "Valoración   -   ${it.voteAverage}"
            viewBinding.movieOverview.text = it.overview
            if (!it.favourite) {
                Log.d("Película favorita: ", "FALSE - ${it.favourite}")
                viewBinding.movieFavourite.setImageResource(R.drawable.empty_star)
            } else {
                Log.d("Película favorita: ", "TRUE -  ${it.favourite}")
                viewBinding.movieFavourite.setImageResource(R.drawable.fill_star)
            }
        }

        // Se actualizan la información cuando viene desde la pantalla de película
        viewModelMovieTranslation.data.observe(this) {
            viewBinding.movieOverviewTraduction.text = it.overview
        }

        // Se obtienen los datos que vienen desde el intent de las películas
        intent.extras?.getParcelable<MoviePresentation>("movie_detail")?.let { movie ->
            setDataFromMovieOfPreviousScreen(movie)
            viewModelMovieDetail.getViewModelDetailMovie(movie.id)
            viewModelMovieTranslation.getViewModelTranslationMovie(movie.id)

            val favouriteMovie = viewBinding.movieFavourite
            favouriteMovie.setBackgroundColor(Color.WHITE)
            favouriteMovie.setOnClickListener {
                viewModelMovieDetail.addRemoveFavouriteDetail(movie.id)
            }
        }


        // Se actualizan la información cuando viene desde la pantalla de serie
        viewModelTvShowDetail.data.observe(this) {
            viewBinding.movieVoteCount.text = "Número total de temporadas   -   ${it.number_of_seasons}"
            viewBinding.movieVoteAverage.text = "Número total de episodios   -   ${it.number_of_episodes}"
            viewBinding.movieOverview.text = it.overview
            if (!it.favourite) {
                Log.d("Serie favorita: ", "FALSE - ${it.favourite}")
                viewBinding.movieFavourite.setImageResource(R.drawable.empty_star)
            } else {
                Log.d("Serie favorita: ", "TRUE - ${it.favourite}")
                viewBinding.movieFavourite.setImageResource(R.drawable.fill_star)
            }

        }

        // Se actualizan la información cuando viene desde la pantalla de serie
        viewModelTvShowTranslation.data.observe(this) {
            viewBinding.movieOverviewTraduction.text = it.overview
        }

        intent.extras?.getParcelable<TvShowPresentation>("tvShow_detail")?.let { tvShow ->
            setDataFromTvShowOfPreviousScreen(tvShow)
            viewModelTvShowDetail.getViewModelDetailTvShow(tvShow.id)
            viewModelTvShowTranslation.getViewModelTranslationTvShow(tvShow.id)

            val favouriteMovie = viewBinding.movieFavourite
            favouriteMovie.setBackgroundColor(Color.WHITE)
            favouriteMovie.setOnClickListener {
                viewModelTvShowDetail.addOrRemoveFavouriteDetail(tvShow.id)
            }
        }


    }




    private fun setDataFromMovieOfPreviousScreen (movie: MoviePresentation) {
        // Se pinta la imagen de la película
        val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
        Glide.with(viewBinding.movieImage.context).load(imageUrl).into(viewBinding.movieImage)
        // Se escribe el título de la película
        viewBinding.movieTitle.text = movie.title
    }

    private fun setDataFromTvShowOfPreviousScreen (tvShow: TvShowPresentation) {
        // Se pinta la imagen de la película
        val imageUrl = "https://image.tmdb.org/t/p/w500/${tvShow.posterPath}"
        Glide.with(viewBinding.movieImage.context).load(imageUrl).into(viewBinding.movieImage)
        // Se escribe el título de la película
        viewBinding.movieTitle.text = tvShow.name
    }


}