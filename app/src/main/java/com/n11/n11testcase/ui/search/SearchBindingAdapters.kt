package com.n11.n11testcase.ui.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n11.n11testcase.domain.model.UserSearchItem
import com.n11.n11testcase.ui.search.adapter.SearchResultsAdapter
import com.n11.n11testcase.utils.GridSpacingItemDecoration
import com.n11.n11testcase.utils.isNull

object SearchBindingAdapters {
    @JvmStatic
    @BindingAdapter(
        "app:setSearchItemResults",
        "app:setSearchListener"
    )
    fun setSearchItemResults(
        rvSearchResults: RecyclerView,
        searchItemResults: ArrayList<UserSearchItem>?,
        listener: SearchListener
    ) {
        searchItemResults?.let {
            if (rvSearchResults.adapter.isNull()) {

                val adapter = SearchResultsAdapter(
                    searchItemResults,
                    listener
                )
                val llManager = GridLayoutManager(rvSearchResults.context, 2)
                rvSearchResults.addItemDecoration(
                    GridSpacingItemDecoration(
                        2
                    )
                )
                rvSearchResults.adapter = adapter
                rvSearchResults.layoutManager = llManager
            } else {
                (rvSearchResults.adapter as SearchResultsAdapter).addItems(
                    searchItemResults
                )
            }
        }
    }
}