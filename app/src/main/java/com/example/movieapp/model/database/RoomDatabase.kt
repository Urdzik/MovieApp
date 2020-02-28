package com.example.movieapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseMovie::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun dao(): DatabaseDao
}