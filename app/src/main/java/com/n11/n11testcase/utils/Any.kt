package com.n11.n11testcase.utils

/**
 * var any:Any? = null
 * if(any.isNull()) -> true
 * any = Any()
 *
 * if(any.isNull()) -> false
 * */
fun Any?.isNull(): Boolean {
    return (this == null)
}
