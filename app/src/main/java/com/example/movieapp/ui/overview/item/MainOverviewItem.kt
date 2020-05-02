package com.example.movieapp.ui.overview.item

import com.example.movieapp.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_overview.*

class MainOverviewItem(
    private val title: String? = "",
    private val onClick: (url: String) -> Unit,
    private val items: List<Item>
) : Item() {

    override fun getLayout() = R.layout.item_overview

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.title.text = title
        viewHolder.recycler.adapter = GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}