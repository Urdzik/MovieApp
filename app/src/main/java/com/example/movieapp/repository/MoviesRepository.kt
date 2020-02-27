package com.example.movieapp.repository

import com.example.movieapp.model.database.MovieDatabase

import com.example.movieapp.model.network.MovieApi
import com.example.movieapp.model.network.asDatabaseModal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val movieDatabase: MovieDatabase) {
    //Refresh the videos stored in the offline cache.
//    suspend fun refreshMovie() {
//        withContext(Dispatchers.IO) {
//            val playList = MovieApi.retrofitService.getPropertyAsync()
//            movieDatabase.movieDao.insert(playList.asDatabaseModal())
//        }
//    }

    //Transformation database object to movie object
    val movies = movieDatabase.movieDao.getMovies()


}