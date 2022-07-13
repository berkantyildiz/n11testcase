package com.n11.n11testcase.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.n11.n11testcase.R
import com.n11.n11testcase.databinding.ItemLoadingBinding
import com.n11.n11testcase.databinding.ItemSearchResultBinding
import com.n11.n11testcase.domain.model.UserSearchItem
import com.n11.n11testcase.ui.search.SearchListener

class SearchResultsAdapter(
    private var searchItemResults: ArrayList<UserSearchItem>,
    private var listener: SearchListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_NORMAL = 1
    }

    private lateinit var binding: ItemSearchResultBinding
    private lateinit var itemLoadingBinding: ItemLoadingBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_result,
                    parent,
                    false
                )
                SearchResultsViewHolder(binding)
            }
            else -> {
                itemLoadingBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_loading,
                    parent,
                    false
                )
                ProgressViewHolder(itemLoadingBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchResultsViewHolder) holder.onBind(searchItemResults[position], position)
        else if (holder is ProgressViewHolder) holder.onBind()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == searchItemResults.size) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
    }


    fun addItems(items: ArrayList<UserSearchItem>) {
        if (items.isNotEmpty()) {
            searchItemResults.addAll(items)
            notifyDataSetChanged()
        }
    }

    inner class SearchResultsViewHolder(var binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: UserSearchItem, position: Int) {
            binding.item = item
            binding.listener = listener
            binding.position = position
            binding.executePendingBindings()
        }
    }

    inner class ProgressViewHolder(var binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() = Unit
    }

    override fun getItemCount(): Int  = searchItemResults.count()

}
