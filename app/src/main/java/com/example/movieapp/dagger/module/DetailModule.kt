package com.example.movieapp.dagger.module

import com.example.movieapp.model.network.NetworkMovie
import com.example.movieapp.ui.detail.DetailActivity

import dagger.Module
import dagger.Provides

@Module
class DetailModule(private val networkMovie: DetailActivity) {

    @Provides
    fun getThisMovie(): DetailActivity = networkMovie

    }
