package com.example.movieapp.presentation.ui.home.overview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.databinding.ItemCustomBinding
import com.example.movieapp.data.model.movie.SmallMovieList
import com.example.movieapp.presentation.ui.home.overview.OverviewFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class ChildAdapter(private val clickListener: MovieListener) :
    ListAdapter<DataItemChild, RecyclerView.ViewHolder>(ChildDiffCallback()) {


    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<SmallMovieList>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItemChild.Header)
                else -> list.map { DataItemChild.MovieItem(it) } + listOf(DataItemChild.Header)
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is MovieViewHolder -> {
                val movieItem = getItem(position) as DataItemChild.MovieItem
                holder.itemView.setOnClickListener {
                    clickListener.onClick(movieItem.movie)
                }

                holder.bind(movieItem.movie)
            }
            is TextViewHolder -> {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> MovieViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItemChild.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItemChild.MovieItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder private constructor(val binding: ItemCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCustomBinding.inflate(layoutInflater)
                binding.button.setOnClickListener {
                    binding.root.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToListFragment("now_playing"))
                }
                return TextViewHolder(binding)
            }
        }
    }

    class MovieViewHolder private constructor(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SmallMovieList) {
            binding.movie = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }
    class MovieListener(val clickListener: (movie: SmallMovieList) -> Unit) {
        fun onClick(movie: SmallMovieList) = clickListener(movie)
    }
}

class ChildDiffCallback : DiffUtil.ItemCallback<DataItemChild>() {
    override fun areItemsTheSame(oldItem: DataItemChild, newItem: DataItemChild): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItemChild, newItem: DataItemChild): Boolean {
        return oldItem == newItem
    }
}


sealed class DataItemChild {
    data class MovieItem(val movie: SmallMovieList) : DataItemChild() {
        override val id = movie.id.toLong()
    }

    object Header : DataItemChild() {
        override val id = Long.MAX_VALUE
    }

    abstract val id: Long
}


