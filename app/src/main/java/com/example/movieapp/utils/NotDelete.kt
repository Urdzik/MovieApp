package com.example.movieapp.utils

import android.view.View

//{
//    val plus18
//    get() = if (adult) View.VISIBLE  else View.GONE
//}

//fun attachPopularMoviesOnScrollListener() {
//    binding.recyclerTopRated.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            val totalItemCount = popularMoviesLayoutMgr.itemCount
//            val visibleItemCount = popularMoviesLayoutMgr.childCount
//            val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()
//
//            if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
//                binding.recyclerTopRated.removeOnScrollListener(this)
//                popularMoviesPage++
//                sendNewMovieList()
//            }
//        }
//    })
//}