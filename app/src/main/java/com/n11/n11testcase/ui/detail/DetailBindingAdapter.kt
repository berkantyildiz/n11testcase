package com.n11.n11testcase.ui.detail

import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.n11.n11testcase.R
import com.n11.n11testcase.domain.model.UserFavorite

object DetailBindingAdapter {

    @JvmStatic
    @BindingAdapter(
        "app:setUserFavorite"
    )
    fun setSearchItemResults(
        floatingActionButton: FloatingActionButton,
        userFavorite: List<UserFavorite>?
    ) {
        userFavorite?.let {
            if (userFavorite.isEmpty()) {
                val icon = R.drawable.ic_baseline_favorite_border_24
                floatingActionButton.setImageResource(icon)
                floatingActionButton.tag = 1
            }
        } ?: run {
            val icon = R.drawable.ic_baseline_favorite_24
            floatingActionButton.setImageResource(icon)
            floatingActionButton.tag = 0
        }
    }
}