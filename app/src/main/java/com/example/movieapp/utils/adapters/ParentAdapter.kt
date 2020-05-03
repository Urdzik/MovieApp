package com.example.movieapp.utils.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ParentItemBinding
import com.example.movieapp.model.network.data.ParentModel

class ParentAdapter : ListAdapter<ParentModel, ParentAdapter.ParentOverviewHolder>(ParentDiffCallback()) {

    private val recyclerPool = RecyclerView.RecycledViewPool()
    lateinit var adapter: MovieAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentOverviewHolder {
        return ParentOverviewHolder(ParentItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ParentOverviewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class ParentOverviewHolder(private val binding: ParentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(parentItem: ParentModel) {
            binding.title.text = parentItem.title
            adapter = binding.recycler.adapter as MovieAdapter
            adapter.addHeaderAndSubmitList(parentItem.children)
            binding.recycler.setRecycledViewPool(recyclerPool)

        }
    }
}

class ParentDiffCallback : DiffUtil.ItemCallback<ParentModel>() {
    override fun areItemsTheSame(oldItem: ParentModel, newItem: ParentModel): Boolean {
        return oldItem.title == newItem.title
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ParentModel, newItem: ParentModel): Boolean {
        return oldItem == newItem
    }
}