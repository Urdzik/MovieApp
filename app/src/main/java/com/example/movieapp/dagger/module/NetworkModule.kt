package com.example.movieapp.dagger.module

import android.app.Application
import android.content.Context
import com.example.movieapp.api.MovieApi
import com.example.movieapp.api.SearchApi
import com.example.movieapp.repository.NetworkRepository
import com.example.movieapp.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @Reusable
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Reusable
    internal fun provideOkHttpClient(context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        val cacheDir = File(context.cacheDir, UUID.randomUUID().toString())

        val cache = Cache(cacheDir, 15 * 1024 * 1024)
        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build() }

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .build()

    @Provides
    @Reusable
    internal fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(
        MovieApi::class.java)

    @Provides
    @Reusable
    internal fun provideNetworkRepository(api: MovieApi): NetworkRepository = NetworkRepositoryImpl(api)


    @Provides
    @Reusable
    internal fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(
        SearchApi::class.java)

}