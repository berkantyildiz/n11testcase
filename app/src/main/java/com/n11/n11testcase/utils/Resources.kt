package com.n11.n11testcase.utils

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun View.hideKeypad() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
