package com.example.movieapp.dagger.component

import com.example.movieapp.dagger.module.NetworkModule
import com.example.movieapp.dagger.module.viewModule.ViewModelModule
import com.example.movieapp.ui.detail.DetailFragment
import com.example.movieapp.ui.list.ListActivity
import com.example.movieapp.ui.overview.OverviewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent{
    fun inject(overviewFragment: OverviewFragment)
    fun inject(detailActivity: DetailFragment)
    fun inject(listActivity: ListActivity)
}