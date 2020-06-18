package com.example.movieapp.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.SearchItemBinding
import com.example.movieapp.model.network.data.search.SearchItem

class SearchAdapter(private val onClickListener: ClickListener) :
    ListAdapter<SearchItem, SearchViewHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(SearchItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener.onClick(getItem(position))
        }
    }

    class ClickListener(val clickListener: (movie: SearchItem) -> Unit) {
        fun onClick(movie: SearchItem) = clickListener(movie)
    }
}

class SearchViewHolder(private val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(searchItem: SearchItem) {
        binding.movie = searchItem
    }
}

class SearchDiffUtil: DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem == newItem
    }
}