package com.n11.n11testcase.ui.common.bindingadapters

import android.view.View
import io.mockk.mockkClass
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class ViewBindingAdapterTest {

    @Before
    fun setUp() {
        mockkObject(ViewBindingAdapter)
    }

    @Test
    fun setVisibility_VerifyVisibleView_SetVisibilityIsTrue() {
        val view = mockkClass(View::class, relaxed = true)
        ViewBindingAdapter.setVisibility(view, true)
        verify {
            view.visibility = View.VISIBLE
        }
    }

    @Test
    fun setVisibility_VerifyNotVisibleView_SetVisibilityIsFalse() {
        val view = mockkClass(View::class, relaxed = true)
        ViewBindingAdapter.setVisibility(view, false)
        verify {
            view.visibility = View.GONE
        }
    }
}
