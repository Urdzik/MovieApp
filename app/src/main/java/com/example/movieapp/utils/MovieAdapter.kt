package com.example.movieapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.model.network.data.NetworkMovie

class MovieAdapter(private val onClickListener: ClickListener) : ListAdapter<NetworkMovie, MovieAdapter.MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class MovieViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: NetworkMovie) {
            binding.movie = movie
        }
    }

    //Class for comparing the old list and the new one, and updating it
    class DiffCallback : DiffUtil.ItemCallback<NetworkMovie>() {
        override fun areItemsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return newItem.id == oldItem.id
        }
    }

    //Class for click by element
    class ClickListener(val clickListener: (movie: NetworkMovie) -> Unit) {
        fun onClick(movie: NetworkMovie) = clickListener(movie)
    }
}