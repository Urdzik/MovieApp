package com.example.movieapp.dagger.module

import com.example.movieapp.model.network.MovieApi
import com.example.movieapp.model.network.RemoteSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl("https://raw.githubusercontent.com/Urdzik/helper/master/")
        .build()

    @Provides
    @Reusable
    internal fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Reusable
    internal fun provideRemoteSource(api: MovieApi): RemoteSource = RemoteSource(api)

}