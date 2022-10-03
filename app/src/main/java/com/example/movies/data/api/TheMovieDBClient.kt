package com.example.movies.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY="50b265fd7a4962e6bfae1e115bcba5d6"
const val BASE_URL="https://api.themoviedb.org/3/"
//const val POSTER_BASE_URL="https://image.tmdb.org/t/p/w342/kAVRgw7GgK1CfYEJq8ME6EvRIgU.jpg"
const val POSTER_BASE_URL="https://image.tmdb.org/t/p/w342"

const val FIRST_PAGE=1
const val POST_PER_PAGE=20


object TheMovieDBClient {
    fun getClient(): TheMovieDBInterface {
        val requestInterceptor=Interceptor{chain ->
        val url=chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

            val request=chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }
        val OkHttpClient= OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .client(OkHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBInterface::class.java)
    }
}