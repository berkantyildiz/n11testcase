package com.n11.n11testcase.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.n11.n11testcase.domain.model.UserDetail
import com.n11.n11testcase.domain.model.UserFavorite
import com.n11.n11testcase.domain.usecase.UserUseCase
import com.n11.n11testcase.ui.search.FragmentSearch.Companion.ARG_LOGIN_ITEM
import com.n11.n11testcase.utils.DataMapper
import com.n11.n11testcase.utils.LoaderState
import com.n11.n11testcase.utils.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    @Assisted private val handle: SavedStateHandle,
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * Loader state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    /**
     * error
     */
    private val _error = MutableLiveData<String>()

    /**
     * Network error
     */
    private val _networkError = MutableLiveData<Boolean>()

    /**
     * User detail remote
     */
    private val _resultUserDetail = MutableLiveData<UserDetail>()
    val resultUserDetail: LiveData<UserDetail>
        get() = _resultUserDetail


    private val _fav = MutableLiveData<List<UserFavorite?>?>()
    val fav: LiveData<List<UserFavorite?>?>
        get() = _fav

    /**
     * Insert to DB
     */
    private val _resultInsertUserToDb = MutableLiveData<Boolean>()
    val resultInsertUserDb: LiveData<Boolean>
        get() = _resultInsertUserToDb

    /**
     * Delete from db
     */
    private val _resultDeleteFromDb = MutableLiveData<Boolean>()
    val resultDeleteFromDb: LiveData<Boolean>
        get() = _resultDeleteFromDb

    /**
     * Remote
     */
    private fun getUserDetailFromApi(username: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCase.getUserDetailFromApi(username).collect {
                _state.value = LoaderState.HideLoading
                when (it) {
                    is ResultState.Success -> _resultUserDetail.postValue(it.data)
                    is ResultState.Error -> _error.postValue(it.error)
                    is ResultState.NetworkError -> _networkError.postValue(true)
                }
            }
        }
    }

    /**
     * Local
     */
    private fun addUserToFavDB(userFavoriteEntity: UserFavorite) {
        val asfasd = ""
        viewModelScope.launch {
            try {
                userUseCase.addUserToFavDB(userFavoriteEntity)
                _resultInsertUserToDb.postValue(true)
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    private fun deleteUserFromDb(userFavoriteEntity: UserFavorite) {
        val asfasd = ""
        viewModelScope.launch {
            try {
                userUseCase.deleteUserFromDb(userFavoriteEntity)
                _resultDeleteFromDb.postValue(true)
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }
/*
    fun getFavUserByUsername(username: String) =
        userUseCase.getFavUserByUsername(username).asLiveData()*/

    private fun getFavUserByUsername(username: String) {
        viewModelScope.launch {
            userUseCase.getFavUserByUsername(username).collect {
                val asdfads = it
                _fav.postValue(it)
            }
        }
    }

    fun beko(tag: Int) {
        when (tag) {
            0 -> {
                resultUserDetail.value?.let {
                    deleteUserFromDb(DataMapper.mapUserDetailToUserFavorite(it))
                }
            }
            1 -> {
                resultUserDetail.value?.let {
                    addUserToFavDB(DataMapper.mapUserDetailToUserFavorite(it))
                }
            }
        }
    }


    init {
        with(handle) {
            val userName = this.get<String>(ARG_LOGIN_ITEM)
            userName?.let {
                getUserDetailFromApi(it)
                getFavUserByUsername(it)
            }
        }
    }
}