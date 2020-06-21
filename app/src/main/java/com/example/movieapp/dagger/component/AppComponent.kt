package com.example.movieapp.dagger.component

import android.app.Application
import com.example.movieapp.MainActivity
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.NetworkModule
import com.example.movieapp.dagger.module.viewModule.ViewModelInjector
import com.example.movieapp.dagger.module.viewModule.ViewModelModule
import com.example.movieapp.ui.home.detail.DetailFragment
import com.example.movieapp.ui.home.list.ListFragment
import com.example.movieapp.ui.home.overview.OverviewFragment
import com.example.movieapp.ui.search.SearchFragment
import com.example.movieapp.ui.user.profile.ProfileFragment
import com.example.movieapp.ui.user.profile.settings.SettingsFragment
import dagger.BindsInstance


import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, AndroidInjectionModule::class, ViewModelInjector::class])
interface AppComponent {

    fun inject(application: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): AppComponent
    }
}


