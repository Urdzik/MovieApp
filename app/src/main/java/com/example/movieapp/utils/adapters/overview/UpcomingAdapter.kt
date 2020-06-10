package com.example.movieapp.utils.adapters.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemRecViewingBinding
import com.example.movieapp.model.network.data.SmallMovie
import com.example.movieapp.utils.adapters.MovieRecDiffCallback

class UpcomingAdapter : ListAdapter<SmallMovie, UpcomingAdapter.UpcomingInnerHolder>(MovieRecDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingInnerHolder {
        return UpcomingInnerHolder(
            ItemRecViewingBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: UpcomingInnerHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UpcomingInnerHolder(
        private val binding: ItemRecViewingBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(smallMovie: SmallMovie) {
            binding.movie = smallMovie
        }
    }
}