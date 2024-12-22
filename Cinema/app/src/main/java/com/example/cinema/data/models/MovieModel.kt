package com.example.cinema.data.models

data class Category(
    val id: String,
    val name: String
)

data class MovieResponse(
    val id: Int,
    val genres: List<GenreResponse>,
    val original_language: String,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val release_date: String,
    val backdrop_path: String,
    val origin_country: List<String>
)

data class MovieModel(
    val id: Int,
    val genre_ids: List<Int>,
    val original_language: String,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String
)

data class Movies(
    val results: List<MovieModel>
)