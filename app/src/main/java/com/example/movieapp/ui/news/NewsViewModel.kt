package com.example.movieapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.news.NewsSource
import com.example.movieapp.model.network.news.data.Result
import com.example.movieapp.utils.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsSource: NewsSource) : ViewModel() {

    private val loadingMap = HashMap<String, Deferred<List<Result>>>()
    private val resultMap = HashMap<String, MutableLiveData<List<Result>>>()

    private val newsTypes = listOf(ALL_NEWS, MOVIE_NEWS, TV_NEWS, PERSON_NEWS)

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            newsTypes.forEach {
//                loadingMap[it] = ioTaskAsync {
//                    newsSource.getWeeklyNews(it)
//                }
            }
        }
    }

    fun loadData() {
        newsTypes.forEach {
            loadTypicalData(it)
        }
    }

    private fun loadTypicalData(type: String) {
        if (!resultMap.contains(type)) {
            resultMap[type] = MutableLiveData()
        }

        viewModelScope.launch {
            loadingMap[type]!!.await().also {
                resultMap[type]!!.postValue(it)
            }
        }
    }

    fun getWeeklyNews(type: String): LiveData<List<Result>> {
        return resultMap[type] ?: MutableLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        loadingMap.values.forEach {
            it.cancel()
        }

        /*dailyNewsMap.entries.zip(weeklyNewsMap.entries).forEach {
            it.first.value.value = null
            it.second.value.value = null
        }*/
    }
}
