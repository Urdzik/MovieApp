package com.example.movieapp.model.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val api: MovieApi
) {
    suspend fun retrieveData(): List<MovieProperty> = withContext(Dispatchers.IO) {
        val playList = api.getPropertyAsync()
        playList
    }
}

