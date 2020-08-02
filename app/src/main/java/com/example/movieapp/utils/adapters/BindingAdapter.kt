package com.example.movieapp.utils.adapters

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.model.network.movie.Genres
import com.example.movieapp.utils.IMAGE_BASE_PATH

//Binding adapter used to display images from URL using Glide
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {

    Glide.with(imageView.context)
        .load("$IMAGE_BASE_PATH$imgUrl")
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(imageView)
}

@BindingAdapter("avaURL")
fun bindAva(imageView: ImageView, imgUrl: Uri?) {

    Glide.with(imageView.context)
        .load(imgUrl)
        .circleCrop()
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(imageView)
}


//Binding adapter used to hide the spinner once data is available.
@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE
    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}



@BindingAdapter("genre")
fun TextView.shopGenres(data: List<Genres>?) {
    data?.let {
        var genres = " "
        var show: String
        for (genre in data) {
            if (data.size == 1) {
                genre.name
            } else {
                if (genres == " ") {
                    show = genre.name
                    genres = show
                } else {
                    show = genres + ", " + genre.name
                    genres = show
                }
                text = show
            }
            }
        }
    }