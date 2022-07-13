package com.n11.n11testcase.ui.common.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:visibility")
    fun setVisibility(view: View, visibility: Boolean) {
        view.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}
