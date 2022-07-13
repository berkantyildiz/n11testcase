package com.n11.n11testcase.utils


sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}