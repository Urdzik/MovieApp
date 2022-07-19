package movie.app.main.presentation.ui.screens.overview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import movie.app.data.model.movie.ParentListMovie
import movie.app.data.model.movie.SmallMovieList
import movie.app.domain.usecase.FetchMovieShortList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val fetchMovieShortList: FetchMovieShortList) :
    ViewModel() {

    private val categoryList = listOf("upcoming", "top_rated", "popular", "now_playing")

    //LiveData object of movie
    private val _navigateToSelectProperty = MutableLiveData<SmallMovieList?>()
    val navigateToSelectProperty: LiveData<SmallMovieList?>
        get() = _navigateToSelectProperty

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    //LiveData of Top Rated movies
    private var _parentListMovie = MutableStateFlow<List<ParentListMovie>>(listOf())
    val parentListMovie: StateFlow<List<ParentListMovie>>
        get() = _parentListMovie


    val errorClickListener = View.OnClickListener { fetchMoviesLists() }

    init {
        fetchMoviesLists()
    }


    private fun fetchMoviesLists() {
        val titleCategoryMap = hashMapOf(
            1 to ("Сейчас в кино" to "now_playing"),
            2 to ("Топ рейтинг" to "top_rated"),
            3 to ("Популярное" to "popular"),
            4 to ("Рекомендации" to "upcoming")
        )

        viewModelScope.launch {
            fetchMovieShortList.invoke(
                categoryList,
                "ru"
            )
                .collect { collectionList ->
                    val mListMovie = collectionList.mapIndexed { index, list ->
                        ParentListMovie(
                            titleCategoryMap[index]?.first ?: "",
                            titleCategoryMap[index]?.second ?: "",
                            list
                        )
                    }
                    _eventNetworkError.postValue(false)
                    _isNetworkErrorShown.postValue(false)
                    _parentListMovie.tryEmit(mListMovie)
                }
        }

    }

    fun displayPropertyDetails(movie: SmallMovieList) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }



}