package com.example.movieapp.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemParentBinding
import com.example.movieapp.model.network.data.ParentListMovie
import com.example.movieapp.ui.home.overview.OverviewViewModel

class OverviewAdapter(private val viewModel: OverviewViewModel) :
    ListAdapter<ParentListMovie, CollectionHolder>(DiffUtilCollection()) {
    private val rvPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder {
        return CollectionHolder(
            ItemParentBinding.inflate(LayoutInflater.from(parent.context)),
            viewModel
        ).apply {
            recyclerView.setRecycledViewPool(rvPool)
        }
    }

    override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CollectionHolder(
    private val binding: ItemParentBinding,
    val viewModel: OverviewViewModel
) : RecyclerView.ViewHolder(binding.root) {

    val recyclerView = binding.childRecycler

    fun bind(parentListMovie: ParentListMovie) {
        val adapter = ChildAdapter(ChildAdapter.MovieListener {
            viewModel.displayPropertyDetails(it)
        })

        binding.title.text = parentListMovie.title
        recyclerView.adapter = adapter

        adapter.addHeaderAndSubmitList(parentListMovie.movieList)
    }

}

class DiffUtilCollection : DiffUtil.ItemCallback<ParentListMovie>() {
    override fun areItemsTheSame(oldItem: ParentListMovie, newItem: ParentListMovie): Boolean {
        return oldItem.category == newItem.category
    }

    override fun areContentsTheSame(oldItem: ParentListMovie, newItem: ParentListMovie): Boolean {
        return oldItem == newItem
    }
}
