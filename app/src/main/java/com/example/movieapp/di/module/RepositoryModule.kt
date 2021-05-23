package com.example.movieapp.di.module

import com.example.movieapp.model.network.MovieApi
import com.example.movieapp.model.network.MovieListSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(includes = [NetworkModule::class])
@InstallIn(ActivityComponent::class)
object RepositoryModule {
   @Provides
   fun provideMovieListSource(movieApi: MovieApi): MovieListSource = MovieListSource(movieApi)
}