package com.example.movieapp.utils.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.databinding.ItemCustomBinding
import com.example.movieapp.databinding.ItemRecViewingBinding
import com.example.movieapp.model.network.data.SmallMovieList
import com.example.movieapp.ui.home.overview.OverviewFragmentDirections
import com.example.movieapp.utils.IMAGE_BASE_PATH
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveInUserAdapter(private val onClickListener: ClickListener) : ListAdapter<SmallMovieList, SaveInUserAdapter.SaveInUserHolder>(SaveInUserDiffCallback()) {


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
