package com.n11.n11testcase.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val space: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        outRect.bottom = space.toPx()
        when {
            position % 2 == 0 -> {
                // Right
                outRect.right = 1.toPx()
                outRect.left = 0.toPx()
            }
            else -> {
                // Left
                outRect.right = 0.toPx()
                outRect.left = 1.toPx()
            }
        }
    }
}
