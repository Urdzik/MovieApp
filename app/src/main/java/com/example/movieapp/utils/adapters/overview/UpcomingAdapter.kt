package com.example.movieapp.utils.adapters.overview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemRecViewingBinding
import com.example.movieapp.model.network.data.movie.SmallMovieList

class UpcomingAdapter : ListAdapter<SmallMovieList, UpcomingAdapter.UpcomingInnerHolder>(MovieRecDiffCallback()) {

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

        fun bind(smallMovie: SmallMovieList) {
            binding.movie = smallMovie
        }
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