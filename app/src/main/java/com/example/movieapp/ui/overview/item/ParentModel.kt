package com.example.movieapp.ui.overview.item

import com.example.movieapp.model.network.data.SmallMovieList

data class ParentModel (
    val title : String = "",
    val children : List<SmallMovieList>
)