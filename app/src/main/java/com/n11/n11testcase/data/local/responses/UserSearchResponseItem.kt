package com.n11.n11testcase.data.local.responses

import com.google.gson.annotations.SerializedName

data class UserSearchResponseItem(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("login")
    val login: String?,
)