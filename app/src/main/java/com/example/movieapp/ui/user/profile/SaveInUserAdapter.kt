package com.example.movieapp.ui.user.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.model.network.movie.SmallMovieList

class SaveInUserAdapter(private val onClickListener: ClickListener) : ListAdapter<SmallMovieList, SaveInUserAdapter.SaveInUserHolder>(
    SaveInUserDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveInUserHolder {
        return SaveInUserHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SaveInUserHolder, position: Int) {
        val item = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    inner class SaveInUserHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SmallMovieList) {
            binding.movie = movie
        }
    }

    class ClickListener(val clickListener: (movie: SmallMovieList) -> Unit) {
        fun onClick(movie: SmallMovieList) = clickListener(movie)
    }
}

class SaveInUserDiffCallback : DiffUtil.ItemCallback<SmallMovieList>() {
    override fun areItemsTheSame(oldItem: SmallMovieList, newItem: SmallMovieList): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SmallMovieList, newItem: SmallMovieList): Boolean {
        return oldItem == newItem
    }
}
