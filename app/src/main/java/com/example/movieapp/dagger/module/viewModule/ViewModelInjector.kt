package com.example.movieapp.dagger.module.viewModule

import com.example.movieapp.MainActivity
import com.example.movieapp.ui.home.detail.DetailFragment
import com.example.movieapp.ui.home.list.ListFragment
import com.example.movieapp.ui.home.overview.OverviewFragment
import com.example.movieapp.ui.search.SearchFragment
import com.example.movieapp.ui.user.profile.ProfileFragment
import com.example.movieapp.ui.user.profile.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModelInjector {
    @ContributesAndroidInjector
    abstract fun contributeActivityAndroidInjector(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSearchFragmentViewModelInjector(): SearchFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeOverviewFragmentViewModelInjector(): OverviewFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeDetailFragmentViewModelInjector(): DetailFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeListFragmentViewModelInjector(): ListFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSettingsFragmentViewModelInjector(): SettingsFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeProfileFragmentViewModelInjector(): ProfileFragment
}

