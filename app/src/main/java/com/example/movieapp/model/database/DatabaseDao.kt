package com.example.movieapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.*


// Database queries
@Dao
interface DatabaseDao {
    @Query("select * from database_movie")
    fun getMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: List<DatabaseMovie>)
}

