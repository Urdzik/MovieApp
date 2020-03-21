package com.example.movieapp.utils.adapters

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemRecViewingBinding
import com.example.movieapp.model.network.data.SmallMovieList

class WeeklyGeneralNewsAdapter : ListAdapter<SmallMovieList, WeeklyGeneralViewHolder>(MovieRecDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyGeneralViewHolder =
        WeeklyGeneralViewHolder(ItemRecViewingBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: WeeklyGeneralViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class WeeklyGeneralViewHolder(private val binding: ItemRecViewingBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: SmallMovieList) {
        Log.d("bind", "bind movie: ${movie.title}")

        binding.movie = movie

        binding.posterImage.setOnClickListener {
//            val intent = Intent(binding.root.context, DetailActivity::class.java)
//            intent.putExtra("movie", it.id)
//            startActivity(binding.root.context, intent, Bundle())
        }
    }
}
