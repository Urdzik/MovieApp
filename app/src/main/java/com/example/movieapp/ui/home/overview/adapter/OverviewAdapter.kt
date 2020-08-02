package com.example.movieapp.ui.home.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemParentBinding
import com.example.movieapp.databinding.ItemRecBinding
import com.example.movieapp.model.network.movie.ParentListMovie
import com.example.movieapp.ui.home.overview.OverviewFragmentDirections
import com.example.movieapp.ui.home.overview.OverviewViewModel

const val UPCOMING_HOLDER = 0
const val DEFAULT_HOLDER = 1

class OverviewAdapter(
    private val viewModel: OverviewViewModel
) : ListAdapter<ParentListMovie, RecyclerView.ViewHolder>(DiffUtilCollection()) {

    private val rvPool = RecyclerView.RecycledViewPool()

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> UPCOMING_HOLDER
            else -> DEFAULT_HOLDER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            UPCOMING_HOLDER -> UpcomingHolder(
                ItemRecBinding.inflate(LayoutInflater.from(parent.context))
            )
            else -> CollectionHolder(
                ItemParentBinding.inflate(LayoutInflater.from(parent.context)), viewModel
            ).apply {
                recyclerView.setRecycledViewPool(rvPool)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            UPCOMING_HOLDER -> (holder as UpcomingHolder).bind(getItem(position))
            else -> (holder as CollectionHolder).bind(getItem(position))
        }
    }
}

class CollectionHolder(
    private val binding: ItemParentBinding,
    val viewModel: OverviewViewModel
) : RecyclerView.ViewHolder(binding.root) {

    val recyclerView = binding.childRecycler

    fun bind(parentListMovie: ParentListMovie) {
        val adapter =
            ChildAdapter(
                ChildAdapter.MovieListener {
                    viewModel.displayPropertyDetails(it)
                })

        binding.title.text = parentListMovie.title
        recyclerView.adapter = adapter

        adapter.addHeaderAndSubmitList(parentListMovie.movieList)
    }
}

class UpcomingHolder(
    private val binding: ItemRecBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parentListMovie: ParentListMovie) {
        val adapter =
            UpcomingAdapter(
                UpcomingAdapter.ClickListener {
                    binding.root.findNavController()
                        .navigate(
                            OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(
                                it.id
                            )
                        )
                })
        binding.piker.adapter = adapter
        adapter.submitList(parentListMovie.movieList)
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
