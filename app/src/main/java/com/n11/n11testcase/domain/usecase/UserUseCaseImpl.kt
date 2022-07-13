package com.n11.n11testcase.domain.usecase

import com.n11.n11testcase.domain.model.UserFavorite
import com.n11.n11testcase.domain.repository.UserRepository
import javax.inject.Inject


class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserUseCase {
    /**
     * Remote
     */
    override suspend fun getUserFromApi(username: String) =
        userRepository.getUserFromApi(username)


    override suspend fun getUserDetailFromApi(username: String) =
        userRepository.getDetailUserFromApi(username)


    override suspend fun getUserFollowers(username: String) =
        userRepository.getUserFollowers(username)


    override suspend fun getUserFollowing(username: String) =
        userRepository.getUserFollowing(username)


    /**
     * Local
     */
    override fun fetchAllUserFavorite() = userRepository.fetchAllUserFavorite()

    override fun getFavUserByUsername(username: String) =
        userRepository.getFavoriteUserByUsername(username)

    override suspend fun deleteUserFromDb(userFavorite: UserFavorite) {
        try {
            userRepository.deleteUserFromFavDB(userFavorite)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        try {
            userRepository.addUserToFavDB(userFavorite)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }


}