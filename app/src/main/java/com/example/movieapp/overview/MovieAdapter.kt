package com.example.movieapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.database.DatabaseMovie
import com.example.movieapp.databinding.ItemBinding

class MovieAdapter(private val onClickListener: ClickListener) :
    ListAdapter<DatabaseMovie, MovieAdapter.MovieViewHolder>(DiffCallback()) {

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

    class MovieViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DatabaseMovie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    //Class for comparing the old list and the new one, and updating it
    class DiffCallback : DiffUtil.ItemCallback<DatabaseMovie>() {
        override fun areItemsTheSame(oldItem: DatabaseMovie, newItem: DatabaseMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DatabaseMovie, newItem: DatabaseMovie): Boolean {
            return newItem.id == oldItem.id
        }
    }

    //Class for click by element
    class ClickListener(val clickListener: (movie: DatabaseMovie) -> Unit) {
        fun onClick(movie: DatabaseMovie) = clickListener(movie)
    }
}