package com.example.movieapp.model.network.data

import com.example.movieapp.model.network.data.SmallMovieList

data class ParentModel (
    val title : String = "",
    val category: String ="",
    val children : List<SmallMovieList>
)