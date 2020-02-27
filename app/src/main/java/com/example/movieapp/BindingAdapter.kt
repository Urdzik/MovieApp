package com.example.movieapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.database.DatabaseMovie

import com.example.movieapp.overview.MovieAdapter

//Binding adapter used to display images from URL using Glide
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl
//            .toUri()
//            .buildUpon()
//            .scheme("https")
//            .build()


        Glide.with(imageView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }



//Binding adapter used to set adapter of RecyclerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DatabaseMovie>?) {
    data?.let {
        val adapter = recyclerView.adapter as MovieAdapter
        adapter.submitList(data)
    }
}

//Binding adapter used to hide the spinner once data is available.
@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE
    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}