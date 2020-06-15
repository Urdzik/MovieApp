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
import com.example.movieapp.model.network.data.SmallMovie
import com.example.movieapp.utils.IMAGE_BASE_PATH

class RecViewingMovieAdapter(private val onClickListener: ClickListener) :
    ListAdapter<SmallMovie, RecViewingMovieAdapter.MovieViewHolder>(MovieRecDiffCallback()) {


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
        fun bind(movie: SmallMovie) {

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

    class ClickListener(val clickListener: (movie: SmallMovie) -> Unit) {
        fun onClick(movie: SmallMovie) = clickListener(movie)
    }
}

class MovieRecDiffCallback : DiffUtil.ItemCallback<SmallMovie>() {
    override fun areItemsTheSame(oldItem: SmallMovie, newItem: SmallMovie): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SmallMovie, newItem: SmallMovie): Boolean {
        return oldItem == newItem
    }
}