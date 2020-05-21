package com.example.movieapp.ui.home.overview.groupie

import com.example.movieapp.R
import com.example.movieapp.databinding.ItemParentBinding
import com.xwray.groupie.databinding.BindableItem
import com.example.movieapp.model.network.data.SmallMovieList

class ParentItem (private val movieList: SmallMovieList ): BindableItem<ItemParentBinding>(){

    override fun getLayout(): Int = R.layout.item_parent

    override fun bind(viewBinding: ItemParentBinding, position: Int) {
        viewBinding.title.text = movieList.title
    }

}