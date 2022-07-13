package com.n11.n11testcase.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.RelativeLayout
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.n11.n11testcase.R
import com.n11.n11testcase.databinding.SearchCustomViewBinding
import com.n11.n11testcase.utils.hideKeypad

class SearchCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    var onSearchClickListener: OnSearchQueryClickListener? = null

    private var binding: SearchCustomViewBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.search_custom_view,
        this,
        true
    )

    init {
        val style = context.obtainStyledAttributes(
            attrs,
            R.styleable.SearchCustomView
        )
        binding.etSearchQuery.hint = style.getString(R.styleable.SearchCustomView_scv_hint)
        setSearchQuery()
        style.recycle()
    }

    private fun setSearchQuery() {
        binding.btnClear.setOnClickListener {
            binding.etSearchQuery.setText("")
            onSearchClickListener?.onSearchClick("")
            binding.etSearchQuery.clearFocus()
            hideKeypad()
        }

        binding.etSearchQuery.onFocusChangeListener = OnFocusChangeListener { _, _ ->
            binding.btnClear.visibility =
                if (binding.etSearchQuery.text.toString().isEmpty()) GONE
                else VISIBLE
        }

        binding.etSearchQuery.setOnEditorActionListener OnEditorActionListener@{ v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchClickListener?.onSearchClick(v.text.toString())
                binding.etSearchQuery.clearFocus()
                hideKeypad()
                return@OnEditorActionListener true
            }
            false
        }

        binding.etSearchQuery.doOnTextChanged { text, _, _, _ ->
            binding.btnClear.visibility =
                if (text?.isEmpty() == true) GONE
                else VISIBLE
        }
    }

    interface OnSearchQueryClickListener {
        fun onSearchClick(searchQuery: String)
    }
}
