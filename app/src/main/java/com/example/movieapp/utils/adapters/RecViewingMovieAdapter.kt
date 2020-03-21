package com.example.movieapp.utils.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemRecViewingBinding
import com.example.movieapp.model.network.data.SmallMovieList
import com.example.movieapp.utils.IMAGE_BASE_PATH

class RecViewingMovieAdapter(private val onClickListener: ClickListener) :
    ListAdapter<SmallMovieList, RecViewingMovieAdapter.MovieViewHolder>(MovieRecDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemRecViewingBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }

        holder.bind(item)
    }

    inner class MovieViewHolder(private val binding: ItemRecViewingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SmallMovieList) {

            Glide.with(binding.root.context)
                .load("$IMAGE_BASE_PATH${movie.backdropPath}")
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.posterImage)
            binding.movie = movie

        }
    }

    class ClickListener(val clickListener: (movie: SmallMovieList) -> Unit) {
        fun onClick(movie: SmallMovieList) = clickListener(movie)
    }
}

class MovieRecDiffCallback : DiffUtil.ItemCallback<SmallMovieList>() {
    override fun areItemsTheSame(oldItem: SmallMovieList, newItem: SmallMovieList): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SmallMovieList, newItem: SmallMovieList): Boolean {
        return oldItem == newItem
    }
}