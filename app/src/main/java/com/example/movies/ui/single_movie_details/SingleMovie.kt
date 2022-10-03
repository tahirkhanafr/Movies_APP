package com.example.movies.ui.single_movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.api.POSTER_BASE_URL
import com.example.movies.data.api.TheMovieDBClient
import com.example.movies.data.api.TheMovieDBInterface
import com.example.movies.data.repository.NetworkState
import com.example.movies.data.vo.MovieDetails
import com.oxcoding.moviemvvm.ui.single_movie_details.MovieDetailsRepository
import com.oxcoding.moviemvvm.ui.single_movie_details.SingleMovieViewModel
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {
    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId: Int = intent.getIntExtra("id",1)
        val apiService: TheMovieDBInterface= TheMovieDBClient.getClient()
        movieRepository= MovieDetailsRepository(apiService)

        viewModel= getViewModel(movieId)

        viewModel.movieDetails.observe(this, androidx.lifecycle.Observer{
            bindUI(it)
        })
        viewModel.networkState.observe(this, androidx.lifecycle.Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })

    }

    private fun bindUI(it: MovieDetails) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        println("checking"+ moviePosterURL)

        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster)
    }


    private fun getViewModel(movieId:Int): SingleMovieViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieRepository,movieId) as T}
        })[SingleMovieViewModel :: class.java]


    }

    }


