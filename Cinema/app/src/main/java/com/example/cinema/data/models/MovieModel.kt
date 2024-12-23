package com.example.cinema.data.models

import com.google.gson.annotations.SerializedName

data class Category(
    val id: String,
    val name: String
)

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("genres") val genres: List<GenreResponse>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("origin_country") val originCountry: List<String>
)

data class MovieModel(
    @SerializedName("id") val id: Int,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String
)

data class Movies(
    val results: List<MovieModel>
)