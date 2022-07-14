package com.n11.n11testcase.ui.search

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n11.n11testcase.domain.model.UserSearchItem
import com.n11.n11testcase.ui.search.adapter.SearchResultsAdapter
import com.n11.n11testcase.utils.*

object SearchBindingAdapters {
    @JvmStatic
    @BindingAdapter(
        "app:setSearchItemResults",
        "app:setSearchListener",
        "app:setLoadingView",
        "app:setState"
    )
    fun setSearchItemResults(
        rvSearchResults: RecyclerView,
        searchItemResults: ArrayList<UserSearchItem>?,
        listener: SearchListener,
        layout: View,
        state: LoaderState?
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

        state?.let {
            if (state is LoaderState.ShowLoading) {
                layout.setVisible()
                rvSearchResults.setGone()
            } else {
                layout.setGone()
                rvSearchResults.setVisible()
            }
        }
    }
}