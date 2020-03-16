package com.example.movieapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope as scope
import com.example.movieapp.model.network.data.SmallMovieList
import com.example.movieapp.model.network.news.NewsSource
import com.example.movieapp.utils.ioReturnTask
import kotlinx.coroutines.launch
import javax.inject.Inject

class GeneralNewsViewModel @Inject constructor(private val newsSource: NewsSource) : ViewModel() {

    val weeklyNewsLiveData: LiveData<List<SmallMovieList>>
        get() = _weeklyNewsLiveData
    private val _weeklyNewsLiveData = MutableLiveData<List<SmallMovieList>>()

    init {
        loadNews()
    }

    private fun loadNews() {
        scope.launch {
            _weeklyNewsLiveData.postValue(ioReturnTask {
                newsSource.getWeeklyNews()
            })
        }
    }
}
