package com.example.movieapp.dagger

import android.app.Application
import com.example.movieapp.dagger.component.AppComponent
import com.example.movieapp.dagger.component.DaggerAppComponent
import com.example.movieapp.dagger.module.NetworkModule

class App : Application(){

    companion object{ lateinit var appComponent: AppComponent }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule(this))
            .build()
    }
}