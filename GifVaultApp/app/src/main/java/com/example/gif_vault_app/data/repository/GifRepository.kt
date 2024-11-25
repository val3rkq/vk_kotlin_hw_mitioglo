package com.example.gif_vault_app.data.repository

import com.example.gif_vault_app.BuildConfig
import com.example.gif_vault_app.data.api.GiphyApi
import com.example.gif_vault_app.data.models.GifItem

interface GifRepository {
    suspend fun getTrendingGifs(limit: Int, offset: Int): Result<List<GifItem>>
}

class GifRepositoryImpl(private val api: GiphyApi) : GifRepository {

    override suspend fun getTrendingGifs(limit: Int, offset: Int): Result<List<GifItem>> {

        return try {
            val response = api.getTrendingGifs(
                apiKey = BuildConfig.GIPHY_API_KEY,
                limit = limit,
                offset = offset
            )
            Result.success(response.data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}