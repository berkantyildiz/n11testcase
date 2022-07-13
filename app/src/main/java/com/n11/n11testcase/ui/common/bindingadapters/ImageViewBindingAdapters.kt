package com.n11.n11testcase.ui.common.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBindingAdapters {
    @JvmStatic
    @BindingAdapter("app:setImageUrl")
    fun setImageUrl(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}