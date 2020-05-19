package com.example.movieapp.dagger.component

import com.example.movieapp.dagger.module.NetworkModule
import com.example.movieapp.dagger.module.viewModule.ViewModelModule
import com.example.movieapp.ui.home.detail.DetailFragment
import com.example.movieapp.ui.home.list.ListFragment
import com.example.movieapp.ui.home.overview.OverviewFragment
import com.example.movieapp.ui.news.detail_news.DetailNewsFragment
import com.example.movieapp.ui.news.news.NewsFragment
import com.example.movieapp.ui.user.profile.ProfileFragment
import com.example.movieapp.ui.user.profile.settings.SettingsFragment


import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent{
    fun inject(overviewFragment: OverviewFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(listFragment: ListFragment)
    fun inject(newsFragment: NewsFragment)
    fun inject(detailNewsFragment: DetailNewsFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(profileFragment: ProfileFragment)
}