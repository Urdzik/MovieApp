package com.example.movieapp.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.model.network.data.Small

class MovieAdapter(private val onClickListener: ClickListener, private var movies: MutableList<Small>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun appendMovies(movies: List<Small>){
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class MovieViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Small) {
            binding.movie = movie
        }
    }


    //Class for click by element
    class ClickListener(val clickListener: (movie: Small) -> Unit) {
        fun onClick(movie: Small) = clickListener(movie)
    }


}