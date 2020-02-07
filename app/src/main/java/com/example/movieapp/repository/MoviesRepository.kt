package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.movieapp.database.MovieDatabase
import com.example.movieapp.database.asDomainModel
import com.example.movieapp.domain.Movie
import com.example.movieapp.network.MovieApi
import com.example.movieapp.network.asDatabaseModal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val movieDatabase: MovieDatabase) {
    //Refresh the videos stored in the offline cache.
    suspend fun refreshMovie() {
        withContext(Dispatchers.IO) {
            val playList = MovieApi.retrofitService.getPropertyAsync()
            movieDatabase.movieDao.insert(playList.asDatabaseModal())
        }
    }

    //Transformation database object to movie object
    val movies: LiveData<List<Movie>> = Transformations.map(movieDatabase.movieDao.getMovies()) {
        it.asDomainModel()
    }
}