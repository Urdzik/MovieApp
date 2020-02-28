package com.example.movieapp.dagger.module

import com.example.movieapp.model.network.NetworkMovie
import com.example.movieapp.ui.detail.DetailActivity

import dagger.Module
import dagger.Provides

@Module
class DetailModule(private val detailFragment: DetailActivity) {

    @Provides
    fun getThisMovie(): NetworkMovie {
        return detailFragment.intent.getParcelableExtra("movie" )
    }
}