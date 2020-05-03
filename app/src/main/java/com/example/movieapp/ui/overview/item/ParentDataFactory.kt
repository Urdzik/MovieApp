package com.example.movieapp.ui.overview.item

object ParentDataFactory {

    private val titleList = arrayListOf("Топ рейтинг", "Популярное", "Сейчас в кино")
    val categoryList = arrayListOf("top_rated", "popular", "now_playing")

    fun title(): String {
        return titleList[0]

    }

    fun category(): String {
        return categoryList[0]
    }

    fun getParent(){

    }


}