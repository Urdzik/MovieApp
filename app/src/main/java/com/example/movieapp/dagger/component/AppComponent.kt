package com.example.movieapp.dagger.component

import com.example.movieapp.dagger.module.NetworkModule
import com.example.movieapp.dagger.module.viewModule.ViewModelModule
import com.example.movieapp.ui.news.NewsFragment
import com.example.movieapp.ui.news.GeneralNewsFragment
import com.example.movieapp.ui.detail.DetailFragment
import com.example.movieapp.ui.list.ListFragment
import com.example.movieapp.ui.overview.OverviewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent{
    fun inject(overviewFragment: OverviewFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(listFragment: ListFragment)
    fun inject(newsFragment: NewsFragment)
    fun inject(userFragment: UserFragment)
    fun inject(detailNewsFragment: DetailNewsFragment)
}