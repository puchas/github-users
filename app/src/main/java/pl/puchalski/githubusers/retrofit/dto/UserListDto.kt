package pl.puchalski.githubusers.retrofit.dto

import com.google.gson.annotations.SerializedName
import pl.puchalski.githubusers.model.User

data class UserListDto(
    @SerializedName("items")
    val users: List<User>?
)