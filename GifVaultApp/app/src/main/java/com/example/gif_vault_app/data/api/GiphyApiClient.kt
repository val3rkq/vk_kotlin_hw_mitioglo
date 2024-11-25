package com.example.gif_vault_app.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GiphyApiClient {

    private val API_BASE_URL = "https://api.giphy.com/"

    val api: GiphyApi = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GiphyApi::class.java)
}
