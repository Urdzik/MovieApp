package com.example.movieapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.network.MovieProperty

class MovieAdapter(private val onClickListener: ClickListener) :
    ListAdapter<MovieProperty, MovieAdapter.MovieViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
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
        fun bind(movieProperty: MovieProperty) {
            binding.property = movieProperty
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieProperty>() {
        override fun areItemsTheSame(oldItem: MovieProperty, newItem: MovieProperty): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieProperty, newItem: MovieProperty): Boolean {
            return newItem.id == oldItem.id
        }

    }
    class ClickListener(val clickListener: (movieProperty: MovieProperty) -> Unit) {
        fun onClick(movieProperty: MovieProperty) = clickListener(movieProperty)
    }
}

