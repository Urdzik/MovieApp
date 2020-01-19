package com.example.movieapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.network.MovieProperty

class MovieAdapter : ListAdapter<MovieProperty, MovieAdapter.MovieViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       val item = getItem(position)
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
}