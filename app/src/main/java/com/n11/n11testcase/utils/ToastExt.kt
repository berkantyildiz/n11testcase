package com.n11.n11testcase.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}