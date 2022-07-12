package com.n11.n11testcase.data.remote

import com.n11.n11testcase.BuildConfig
import com.n11.n11testcase.data.local.responses.SearchUserResponse
import com.n11.n11testcase.data.local.responses.UserDetailResponse
import com.n11.n11testcase.data.local.responses.UserFollowersResponse
import com.n11.n11testcase.data.local.responses.UserFollowingResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {


    @GET("search/users?")
    suspend fun getSearchUser(
        @Query("q") q : String
    ) : SearchUserResponse

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    suspend fun getDetailUser(
        @Path("username") username: String
    ) : UserDetailResponse


    @GET("users/{username}/followers")
    suspend fun getFollowerUser(
        @Path("username") username: String
    ) : UserFollowersResponse

    @GET("users/{username}/following")
    suspend fun getFollowingUser(
        @Path("username") username: String
    ) : UserFollowingResponse


}