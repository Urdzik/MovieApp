package com.example.movieapp

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.domian.Movie
import com.example.movieapp.overview.MovieAdapter
import com.example.movieapp.overview.MovieApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("title")
fun bindTitle(textView: TextView, title: String?) {
    title?.let {
        textView.text = title
    }
}

@BindingAdapter("plot")
fun TextView.bindPlot(plot: String?) {
    plot?.let {
        text = plot
    }
}

@BindingAdapter("year")
fun TextView.bindYear(year: Int?) {
    year?.let {
        text = year.toString()
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}


@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE
    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}

















