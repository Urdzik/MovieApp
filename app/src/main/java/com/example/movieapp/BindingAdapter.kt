package com.example.movieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.domain.Movie
import com.example.movieapp.overview.MovieAdapter

//Binding adapter used to display images from URL using Glide
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

//Binding adapter used to display title from object
@BindingAdapter("title")
fun bindTitle(textView: TextView, title: String?) {
    title?.let {
        textView.text = title
    }
}

//Binding adapter used to display plot from object
@BindingAdapter("plot")
fun TextView.bindPlot(plot: String?) {
    plot?.let {
        text = plot
    }
}

//Binding adapter used to display year from object
@BindingAdapter("year")
fun TextView.bindYear(year: Int?) {
    year?.let {
        text = year.toString()
    }
}

//Binding adapter used to display time from object
@BindingAdapter("time")
fun TextView.bindTime(time: String?) {
    time?.let {
        text = time
    }
}

//Binding adapter used to display language from object
@BindingAdapter("language")
fun TextView.bindLanguage(language: String?) {
    language?.let {
        text = language
    }
}

//Binding adapter used to display writer from object
@BindingAdapter("writer")
fun TextView.bindWriter(writer: String?) {
    writer?.let {
        text = writer
    }
}

//Binding adapter used to display actors from object
@BindingAdapter("actors")
fun TextView.bindActors(actors: String?) {
    actors?.let {
        text = actors
    }
}

//Binding adapter used to display genre from object
@BindingAdapter("genre")
fun TextView.bindGenre(genre: String?) {
    genre?.let {
        text = genre
    }
}


//Binding adapter used to set adapter of RecyclerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
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

















