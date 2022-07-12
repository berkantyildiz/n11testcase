package com.n11.n11testcase.data

import com.n11.n11testcase.data.local.db.dao.UserFavoriteDao
import com.n11.n11testcase.data.remote.NetworkService
import com.n11.n11testcase.domain.model.*
import com.n11.n11testcase.domain.repository.UserRepository
import com.n11.n11testcase.utils.DataMapper
import com.n11.n11testcase.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val userFavoriteDao: UserFavoriteDao
) : UserRepository {

    /**
     * Remote
     */
    override suspend fun getUserFromApi(username: String): Flow<ResultState<List<UserSearchItem>>> {
        return flow {
            try {
                val response = networkService.getSearchUser(username)
                val userItems = response.userItems
                val dataMaped = userItems?.let { listSearchUser ->
                    DataMapper.mapUserSearchResponseToDomain(listSearchUser)
                }
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailUserFromApi(username: String): Flow<ResultState<UserDetail>> {
        return flow {
            try {
                val response = networkService.getDetailUser(username)
                val dataMaped = DataMapper.mapUserDetailResponseToDomain(response)
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserFollowers(username: String): Flow<ResultState<List<UserFollower>>> {
        return flow {
            try {
                val response = networkService.getFollowerUser(username)
                val mapedData = DataMapper.mapUserFollowerResponseToDomain(response)
                emit(ResultState.Success(mapedData))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserFollowing(username: String): Flow<ResultState<List<UserFollowing>>> {
        return flow {
            try {
                val response = networkService.getFollowingUser(username)
                val dataMaped = DataMapper.mapUserFollowingResponseToDomain(response)
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }


    /**
     * Local
     */
    override fun fetchAllUserFavorite(): Flow<List<UserFavorite>> {
        return userFavoriteDao.fetchAllUsers().map {
            DataMapper.mapUserFavoriteEntitiesToDomain(it)
        }
    }

    override fun getFavoriteUserByUsername(username: String): Flow<List<UserFavorite>> {
        return userFavoriteDao.getFavByUsername(username).map {
            DataMapper.mapUserFavoriteEntitiesToDomain(it)
        }
    }

    override suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        val data = DataMapper.mapUserFavoriteDomainToEntity(userFavorite)
        return userFavoriteDao.addUserToFavoriteDB(data)
    }

    override suspend fun deleteUserFromFavDB(userFavorite: UserFavorite) {
        val data = DataMapper.mapUserFavoriteDomainToEntity(userFavorite)
        return userFavoriteDao.deleteUserFromFavoriteDB(data)
    }


}