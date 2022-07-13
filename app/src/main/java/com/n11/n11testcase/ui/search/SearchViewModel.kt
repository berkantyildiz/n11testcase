package com.n11.n11testcase.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n11.n11testcase.domain.model.UserSearchItem
import com.n11.n11testcase.domain.usecase.UserUseCase
import com.n11.n11testcase.utils.LoaderState
import com.n11.n11testcase.utils.ResultState
import com.n11.n11testcase.utils.SingleLiveDataEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _error = MutableLiveData<String>()

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean>
        get() = _networkError

    private val _resultUserApi = MutableLiveData<ArrayList<UserSearchItem>>()
    val resultUserApi: LiveData<ArrayList<UserSearchItem>>
        get() = _resultUserApi

    var navigateToDetail = MutableLiveData<SingleLiveDataEvent<UserSearchItem>>()


    private fun getUserFromApi(query: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCase.getUserFromApi(query).collect {
                when (it) {
                    is ResultState.Success -> {
                        _resultUserApi.postValue(it.data as ArrayList<UserSearchItem>?)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.Error -> {
                        _error.postValue(it.error)
                    }
                    is ResultState.NetworkError -> {
                        _networkError.postValue(true)
                    }
                }
            }
        }
    }

    var onClickSearchItem = object : SearchListener {
        override fun onClickSearchItem(position: Int) {
            resultUserApi.value?.let {
                navigateToDetail.value = SingleLiveDataEvent(it[position])
            }
        }
    }

    fun searchRequest(search: String) {
        if (search.trim().length < 2) return
        getUserFromApi(search)
    }


}
