package com.example.cinema.data.repository

import com.example.cinema.data.api.MoviesApi
import com.example.cinema.data.models.MovieResponse
import com.example.cinema.data.models.Movies

interface MoviesRepository {
    suspend fun getPopularMovies(): Result<Movies>

    suspend fun getNowPlayingMovies(): Result<Movies>

    suspend fun getTopRatedMovies(): Result<Movies>

    suspend fun getUpcomingMovies(): Result<Movies>

    suspend fun getMovieInfo(movieId: Int): Result<MovieResponse>
}

class MoviesRepositoryImpl(private val api: MoviesApi) : MoviesRepository {

    private val bearerToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZjU0MDQ1NjIxMTQwMDA1YzgxMjA4ZjcxOGQxOWJhOCIsIm5iZiI6MTczNDM0MzM1MS4wMzMsInN1YiI6IjY3NWZmYWI3YzJiMjdlZjNiZDIxZTJjNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mzWWbtTd9ctXELG44yXhJ6yLwXW2HPA77vPF8hT4Brc"

    override suspend fun getPopularMovies(): Result<Movies> {
        return try {
            val response = api.getPopularMovies(
                accept = "application/json",
                token = bearerToken,
            )

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getNowPlayingMovies() : Result<Movies> {
        return try {
            val response = api.getNowPlayingMovies(
                accept = "application/json",
                token = bearerToken,
            )

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTopRatedMovies(): Result<Movies> {
        return try {
            val response = api.getTopRatedMovies(
                accept = "application/json",
                token = bearerToken,
            )

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUpcomingMovies(): Result<Movies> {
        return try {
            val response = api.getUpcomingMovies(
                accept = "application/json",
                token = bearerToken,
            )

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieInfo(movieId: Int): Result<MovieResponse> {
        return try {
            val response = api.getMovieDetails(
                accept = "application/json",
                token = bearerToken,
                id = movieId
            )

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}