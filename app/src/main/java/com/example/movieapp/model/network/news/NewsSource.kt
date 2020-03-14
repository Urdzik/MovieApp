package com.example.movieapp.model.network.news

import android.util.Log
import com.example.movieapp.model.network.data.NetworkMovie
import com.example.movieapp.model.network.news.data.Result
import com.example.movieapp.utils.ALL_NEWS
import com.example.movieapp.utils.DAILY_NEWS
import com.example.movieapp.utils.WEEKLY_NEWS
import com.example.movieapp.utils.ioReturnTask
import javax.inject.Inject

class NewsSource @Inject constructor(private val api: NewsApi) {

    suspend fun getDailyNews(type: String = ALL_NEWS): List<Result> {
        val newsResponse = api.getNews(type, DAILY_NEWS)

        return if (newsResponse.results.isNullOrEmpty()) {
            emptyList()
        } else {
            newsResponse.results
        }
    }

    suspend fun getWeeklyNews(type: String = ALL_NEWS): List<Result> = ioReturnTask {
        val newsResponse = api.getNews(type, WEEKLY_NEWS)

        if (newsResponse.results.isNullOrEmpty()) {
            emptyList()
        } else {
            newsResponse.results
        }
    }
}