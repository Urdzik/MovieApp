package com.example.movieapp.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.model.network.data.SmallMovieList

class PopularMovieAdapter(
    private val onClickListener: ClickListener,
    private var movies: MutableList<SmallMovieList>
) : RecyclerView.Adapter<PopularMovieAdapter.MovieViewHolder>() {

    fun appendMovies(movies: List<SmallMovieList>) {
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

    override fun getItemCount(): Int = movies.size - 4

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class MovieViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SmallMovieList) {
            binding.movie = movie
        }
    }


    //Class for click by element
    class ClickListener(val clickListener: (movie: SmallMovieList) -> Unit) {
        fun onClick(movie: SmallMovieList) = clickListener(movie)
    }


}