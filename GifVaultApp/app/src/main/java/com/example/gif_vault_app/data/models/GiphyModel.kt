package com.example.gif_vault_app.data.models

data class GiphyResponse(
    val data: List<GifItem>,
    val pagination: Pagination
)

data class GifItem(
    val id: String,
    val images: GifImages
)

data class GifImages(
    val original: GifImage
)

data class GifImage(
    val url: String,
    val width: String,
    val height: String
)

data class Pagination(
    val total_count: Int,
    val count: Int,
    val offset: Int
)
