package com.example.movieapp.dagger.module

import android.app.Application
import com.example.movieapp.model.network.MovieApi
import com.example.movieapp.model.network.news.NewsApi
import com.example.movieapp.model.network.news.NewsSource
import com.example.movieapp.model.network.MovieListSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

@Module
class NetworkModule (private val application: Application) {

    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        // 15 MiB cache
        val cache = Cache(cacheDir, 15 * 1024 * 1024)
        return OkHttpClient.Builder()
            .cache(
                cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build() }

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .build()

    @Provides
    @Reusable
    internal fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Reusable
    internal fun provideRemoteSource(api: MovieApi): MovieListSource = MovieListSource(api)

    @Provides
    @Reusable
    internal fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    @Reusable
    internal fun provideNewsSource(api: NewsApi): NewsSource = NewsSource(api)
}