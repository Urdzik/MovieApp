package com.example.movieapp.ui.news.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope as scope
import com.example.movieapp.model.network.data.SmallMovieList
import com.example.movieapp.model.network.news.NewsSource
import com.example.movieapp.model.network.news.data.TvNew
import com.example.movieapp.utils.ioReturnTask
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsSource: NewsSource) : ViewModel() {

    val weeklyNewsLiveData: LiveData<List<SmallMovieList>>
        get() = _weeklyNewsLiveData
    private val _weeklyNewsLiveData = MutableLiveData<List<SmallMovieList>>()

    val tvNewsLiveData: LiveData<List<TvNew>>
        get() = _tvNewsLiveData
    private val _tvNewsLiveData = MutableLiveData<List<TvNew>>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _isLoading = MutableLiveData<Boolean>()

    val redirectToAllNewsLiveData: LiveData<Any>
        get() = _redirectToAllNewsLiveData
    private val _redirectToAllNewsLiveData = MutableLiveData<Any>()

    init {
        loadNews()
    }

    private fun loadNews() {
        _isLoading.value = true
        scope.launch {
            val news = ioReturnTask {
                newsSource.getWeeklyNews()
            }
            _isLoading.postValue(false)
            _weeklyNewsLiveData.postValue(news)
        }

        scope.launch {
            val news = ioReturnTask {
                newsSource.getTvNews()
            }
            _isLoading.postValue(false)
            _tvNewsLiveData.postValue(news)
        }
    }

    fun redirectToAllNews() {
        _redirectToAllNewsLiveData.postValue(Any())
    }

    fun redirectComplete() {
        _redirectToAllNewsLiveData.value = null
    }
}
