package com.example.cinema.data.api

import com.example.cinema.data.models.MovieModel
import com.example.cinema.data.models.MovieResponse
import com.example.cinema.data.models.Movies
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Header("accept") accept: String,
        @Header("Authorization") token: String
    ): Movies

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Header("accept") accept: String,
        @Header("Authorization") token: String
    ): Movies

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Header("accept") accept: String,
        @Header("Authorization") token: String
    ): Movies

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Header("accept") accept: String,
        @Header("Authorization") token: String
    ): Movies

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Header("accept") accept: String,
        @Header("Authorization") token: String,
        @Path("movie_id") id: Int
    ): MovieResponse
}