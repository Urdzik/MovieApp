package com.example.movieapp.dagger.component

import android.app.Application
import com.example.movieapp.dagger.module.NetworkModule
import com.example.movieapp.dagger.module.viewModule.ViewModelModule
import com.example.movieapp.ui.detail.DetailFragment
import com.example.movieapp.ui.overview.OverviewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent{
    fun overviewInject(overviewFragment: OverviewFragment)
    fun detailInject(detailFragment: DetailFragment)
}