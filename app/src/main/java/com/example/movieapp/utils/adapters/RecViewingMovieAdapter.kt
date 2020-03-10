package com.example.movieapp.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R

import com.example.movieapp.databinding.ItemRecViewingBinding
import com.example.movieapp.model.network.data.SmallMovieList

class RecViewingMovieAdapter(
    private val onClickListener: ClickListener,
    private var movies: MutableList<SmallMovieList>
) : RecyclerView.Adapter<RecViewingMovieAdapter.MovieViewHolder>() {

    fun appendMovies(movies: List<SmallMovieList>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemRecViewingBinding.inflate(LayoutInflater.from(parent.context)
        )
        )
    }

    override fun getItemCount(): Int = movies.size - 16

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class MovieViewHolder(private val binding: ItemRecViewingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SmallMovieList) {
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.posterImage)
            binding.movie = movie
        }
    }


    //Class for click by element
    class ClickListener(val clickListener: (movie: SmallMovieList) -> Unit) {
        fun onClick(movie: SmallMovieList) = clickListener(movie)
    }


}