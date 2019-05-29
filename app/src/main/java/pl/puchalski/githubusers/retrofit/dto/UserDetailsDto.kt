package pl.puchalski.githubusers.retrofit.dto

import com.google.gson.annotations.SerializedName

class UserDetailsDto {
    var login = ""
    @SerializedName("avatar_url")
    var avatarUrl = ""
    var name: String? = ""
    var blog: String? = ""
    var location: String? = ""
}