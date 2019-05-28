package pl.puchalski.githubusers.retrofit.dto

import com.google.gson.annotations.SerializedName

class UserListDto {
    @SerializedName("items")
    var users : List<UserDto>? = null
}