package com.n11.n11testcase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    private lateinit var binding: T

    open fun onViewBind(binding: T, savedInstanceState: Bundle?) {}

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, layoutId, container, false
        ).apply {
            lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        } as T
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewBind(binding, savedInstanceState)
    }
}

