package com.example.movieapp.ui.home.overview.groupie

import com.example.movieapp.R
import com.example.movieapp.databinding.ItemParentBinding
import com.example.movieapp.model.network.data.ParentListMovie
import com.example.movieapp.model.network.data.SmallMovie
import com.xwray.groupie.databinding.BindableItem

//Work with recommended RV
//    private fun recViewingRvViewing() {
//        binding.piker.adapter =
//            RecViewingMovieAdapter(RecViewingMovieAdapter.ClickListener {
//                viewModel.displayPropertyDetails(it)
//            })
//
//        //Add animation for RV
//        binding.piker.setItemTransformer(
//            ScaleTransformer.Builder()
//                .setMaxScale(1.05f)
//                .setMinScale(0.8f)
//                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
//                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
//                .build()
//        )
//
//        recViewingMovieAdapter = binding.piker.adapter as RecViewingMovieAdapter
//
//        viewModel.recViewingPlayList.observe(viewLifecycleOwner, Observer {
//            recViewingMovieAdapter.submitList(it)
//        })
//    }
//

//                networkSource.fetchSmallMovieList("upcoming", "26f381d6ab8dd659b22d983cab9aa255", "ru"),

class HeaderItem (private val movie: List<SmallMovie>): BindableItem<ItemParentBinding>() {
    override fun getLayout(): Int = R.layout.item_rec

    override fun bind(viewBinding: ItemParentBinding, position: Int) {
        TODO("Not yet implemented")
    }
}