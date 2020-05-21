package com.example.movieapp.ui.home.overview.groupie

import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemParentBinding
import com.example.movieapp.model.network.data.ParentListMovie
import com.xwray.groupie.databinding.BindableItem
import com.example.movieapp.model.network.data.SmallMovieList
import com.example.movieapp.ui.home.overview.OverviewFragmentDirections
import com.example.movieapp.utils.adapters.ChildAdapter
import com.example.movieapp.utils.adapters.ChildAdapter.*
import com.example.movieapp.utils.adapters.TopRatedMovieAdapter

class ParentItem (private val movieList: ParentListMovie): BindableItem<ItemParentBinding>(){

    private lateinit var childAdapter: ChildAdapter

    override fun getLayout(): Int = R.layout.item_parent

    override fun bind(viewBinding: ItemParentBinding, position: Int) {
        viewBinding.title.text = movieList.title
        println(movieList.title)

        viewBinding.childRecycler.adapter = ChildAdapter(MovieListener {
//           viewBinding.root.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment())
        })

        childAdapter = viewBinding.childRecycler.adapter as ChildAdapter


        childAdapter.addHeaderAndSubmitList(movieList.movieList)

    }

}