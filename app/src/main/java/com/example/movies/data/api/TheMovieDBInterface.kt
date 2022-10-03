package com.example.movies.data.api

import com.example.movies.data.vo.MovieDetails
import com.example.movies.data.vo.MovieResponce
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    //https://api.themoviedb.org/3/movie/popular?api_key=50b265fd7a4962e6bfae1e115bcba5d6 //Full popular movie api
    //https://api.themoviedb.org/3/movie/507086?api_key=50b265fd7a4962e6bfae1e115bcba5d6&language=en-US
    //https://api.themoviedb.org/3/  //Base url

    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page: Int): Single<MovieResponce>

    @GET("movie/{movie_id}")
    fun  getMoviesDetails(@Path("movie_id")  id:Int): Single<MovieDetails>


}