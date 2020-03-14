package com.example.movieapp.model.network.news

import com.example.movieapp.model.network.data.Results
import com.example.movieapp.model.network.news.data.NewsResponse
import com.example.movieapp.utils.ALL_NEWS
import com.example.movieapp.utils.API_KEY
import com.example.movieapp.utils.WEEKLY_NEWS
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {
    @GET("trending/{type}/{time}?api_key=$API_KEY")
    suspend fun getNews(
        @Path("type") type: String = ALL_NEWS,
        @Path("time") time: String = WEEKLY_NEWS
    ): NewsResponse
}