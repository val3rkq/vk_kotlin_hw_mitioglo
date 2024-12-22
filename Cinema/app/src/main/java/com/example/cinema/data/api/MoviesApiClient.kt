package com.example.cinema.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApiClient {

    private const val API_BASE_URL = "https://api.themoviedb.org/"

    val api: MoviesApi = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesApi::class.java)
}