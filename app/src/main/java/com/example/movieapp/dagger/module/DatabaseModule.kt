package com.example.movieapp.dagger.module

import android.content.Context
import androidx.room.Room

import com.example.movieapp.model.database.DatabaseDao
import com.example.movieapp.model.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Reusable
    fun provideDatabase() = Room.databaseBuilder(
        context.applicationContext,
        MovieDatabase::class.java,
        "movies"
    ).fallbackToDestructiveMigration().build()


    @Provides
    @Reusable
    fun provideDao(movieDatabase: MovieDatabase): DatabaseDao = movieDatabase.dao()


}