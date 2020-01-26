package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.movieapp.database.MovieDatabase
import com.example.movieapp.database.asDomainModel
import com.example.movieapp.domian.Movie
import com.example.movieapp.network.MovieApi
import com.example.movieapp.network.asDatabaseModal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val movieDatabase: MovieDatabase){
    suspend fun refereshMovie(){
        withContext(Dispatchers.IO){
            val playList = MovieApi.retrofitService.getPropertyAsync().await()
            movieDatabase.movieDao.insert(playList.asDatabaseModal())
        }
    }
    val movies: LiveData<List<Movie>> = Transformations.map(movieDatabase.movieDao.getMovies()){
        it.asDomainModel()
    }
}
