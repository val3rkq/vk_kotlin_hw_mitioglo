package com.example.cinema.data.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class FavouriteMovie : RealmObject {
    @PrimaryKey
    var movieId: Int = 0
}

class FavouriteRepository {

    private val realm: Realm by lazy {
        Realm.open(
            RealmConfiguration.Builder(setOf(FavouriteMovie::class))
                .name("favourites.realm")
                .build()
        )
    }

    fun getFavourites(): List<Int> {
        return realm.query<FavouriteMovie>().find().map { it.movieId }
    }

    fun addFavourite(movieId: Int) {
        if (!getFavourites().contains(movieId)) {
            realm.writeBlocking {
                copyToRealm(FavouriteMovie().apply { this.movieId = movieId })
            }
        }
    }

    fun removeFavourite(movieId: Int) {
        realm.writeBlocking {
            val movieToDelete = realm.query<FavouriteMovie>("movieId == $0", movieId).first().find()
            movieToDelete?.let { delete(it) }
        }
    }

    fun close() {
        realm.close()
    }
}
