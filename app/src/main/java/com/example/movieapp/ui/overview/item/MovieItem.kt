package com.example.movieapp.ui.overview.item

import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.network.data.SmallMovieList
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item.*
import kotlinx.android.synthetic.main.item_overview.*

class MovieItem(private val content: SmallMovieList) : Item() {

    override fun getLayout() = R.layout.item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.title.text = content.title
        Glide.with(viewHolder.poster_image)
            .load(content.posterPath)
            .into(viewHolder.poster_image)
    }
}