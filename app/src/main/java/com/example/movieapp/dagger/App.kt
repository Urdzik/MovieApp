package com.example.movieapp.dagger

import android.app.Activity
import android.app.Application
import com.example.movieapp.dagger.component.AppComponent
import com.example.movieapp.dagger.component.DaggerAppComponent
import com.example.movieapp.dagger.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector as AndroidInjector<Any>

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .bindApplication(this)
            .build()
            .apply { inject(this@App) }
    }
}