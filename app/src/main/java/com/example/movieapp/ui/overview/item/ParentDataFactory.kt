package com.example.movieapp.ui.overview.item

import com.example.movieapp.model.network.data.ParentModel
import com.example.movieapp.model.network.data.SmallMovieList

object ParentDataFactory {

    private val titleList = arrayListOf("Топ рейтинг", "Популярное", "Сейчас в кино")
    private val categoryList = arrayListOf("top_rated", "popular", "now_playing")

    fun title(): String {
        return titleList[0]

    }

    fun category(): String {
        return categoryList[0]
    }

    private fun children(): List<SmallMovieList> {
        return MovieDataFactory.getChildren(category())
    }

    fun getParent(): List<ParentModel> {
        val parents = mutableListOf<ParentModel>()
        repeat(titleList.size) {
            val parent = ParentModel(title(), children())
            parents.add(parent)
        }
        return parents
    }


}