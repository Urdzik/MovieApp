package com.example.movieapp.model.network.news

import com.example.movieapp.model.network.data.Results
import com.example.movieapp.model.network.data.SmallMovie
import com.example.movieapp.model.network.news.data.NewsResponse
import com.example.movieapp.model.network.news.data.TvNews
import com.example.movieapp.utils.ALL_NEWS
import com.example.movieapp.utils.API_KEY
import com.example.movieapp.utils.WEEKLY_NEWS
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {
    @GET("trending/{type}/{time}?api_key=$API_KEY")
    suspend fun getNews(
        @Path("type") type: String,
        @Path("time") time: String
    ): SmallMovie

    @GET("trending/tv/{time}?api_key=$API_KEY")
    suspend fun getTvNews(
        @Path("time") type: String
    ): TvNews
}