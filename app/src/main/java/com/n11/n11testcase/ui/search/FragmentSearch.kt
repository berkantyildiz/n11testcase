package com.n11.n11testcase.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.n11.n11testcase.R
import com.n11.n11testcase.databinding.FragmentSearchBinding
import com.n11.n11testcase.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearch : BaseFragment<FragmentSearchBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_search

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewBind(binding: FragmentSearchBinding, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        viewModel.navigateToDetail.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { ci ->
                val bundle = Bundle().apply {
                    putString(ARG_LOGIN_ITEM, ci.login)
                }
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_fragmentSearch_to_fragmentDetail, bundle)
            }
        }
    }

    companion object {
        const val ARG_LOGIN_ITEM = "login_item"

    }
}