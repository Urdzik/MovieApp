package com.example.movieapp.utils.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemCustomBinding
import com.example.movieapp.databinding.ItemTvNewsBinding
import com.example.movieapp.model.network.news.data.TvNew

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class TvNewsAdapter(private val seeAllListener: SeeAllListener) : ListAdapter<TvNewsItem, RecyclerView.ViewHolder>(TvNewsDiffCallback()) {

//    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<TvNew>?) {
//        adapterScope.launch {
            val items = when (list) {
                null -> listOf(TvNewsItem.Header)
                else -> list.map { TvNewsItem.MovieItem(it) } + listOf(TvNewsItem.Header)
            }

//            withContext(Dispatchers.Main) {
                submitList(items)
//            }
//        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is MovieViewHolder -> {
                val movieItem = getItem(position) as TvNewsItem.MovieItem
                holder.itemView.setOnClickListener {

                }

                holder.bind(movieItem.tvNew)
            }
            is TextViewHolder -> {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent, seeAllListener)
            ITEM_VIEW_TYPE_ITEM -> MovieViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TvNewsItem.Header -> ITEM_VIEW_TYPE_HEADER
            is TvNewsItem.MovieItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder private constructor(val binding: ItemCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, seeAllListener: SeeAllListener): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCustomBinding.inflate(layoutInflater)
                binding.button.setOnClickListener {
//                    val intent = Intent(binding.root.context, AllNewsActivity::class.java)
//                    binding.root.context.startActivity(intent)
                    seeAllListener.onClick()
                }

                return TextViewHolder(binding)
            }
        }
    }

    class MovieViewHolder private constructor(val binding: ItemTvNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TvNew) {
            binding.tvNew = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTvNewsBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }
}

class TvNewsDiffCallback : DiffUtil.ItemCallback<TvNewsItem>() {

    override fun areItemsTheSame(oldItem: TvNewsItem, newItem: TvNewsItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: TvNewsItem, newItem: TvNewsItem): Boolean {
        return oldItem == newItem
    }
}

//
//
sealed class TvNewsItem {
    data class MovieItem(val tvNew: TvNew) : TvNewsItem() {
        override val id = tvNew.id.toLong()
    }

    object Header : TvNewsItem() {
        override val id = Long.MAX_VALUE
    }

    abstract val id: Long
}
//
class SeeAllListener(val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}
//
