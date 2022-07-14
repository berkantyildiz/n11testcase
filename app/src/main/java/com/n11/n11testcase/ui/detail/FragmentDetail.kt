package com.n11.n11testcase.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.n11.n11testcase.R
import com.n11.n11testcase.databinding.FragmentDetailBinding
import com.n11.n11testcase.ui.BaseFragment
import com.n11.n11testcase.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : BaseFragment<FragmentDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_detail

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewBind(binding: FragmentDetailBinding, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        binding.favButton.setOnClickListener {
            viewModel.setFavorite(it.tag as Int)
        }

        viewModel.resultInsertUserDb.observe(viewLifecycleOwner) {
            if (it) {
                toast(getString(R.string.user_success))
            }
        }

    }
}