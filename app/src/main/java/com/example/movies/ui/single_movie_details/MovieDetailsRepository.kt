package com.oxcoding.moviemvvm.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.example.movies.data.api.TheMovieDBInterface
import com.example.movies.data.repository.NetworkState
import com.example.movies.data.vo.MovieDetails
import com.oxcoding.moviemvvm.data.repository.MovieDetailsNetworkDataSource
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
    //local data to fetch you can do it through this class



}