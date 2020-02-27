package com.example.movieapp.model.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


// Database queries
@Dao
interface MovieDao {
    @Query("select * from database_movie")
    fun getMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: List<DatabaseMovie>)
}

//Create DAO object
@Database(entities = [DatabaseMovie::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}


private lateinit var INSTANCE: MovieDatabase

// Function create database
fun getDatabase(context: Context): MovieDatabase {
    synchronized(MovieDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movies"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}