package pl.puchalski.githubusers.model

data class UserDetails(
    val login: String,
    val avatar_url: String,
    val name: String?,
    val blog: String?,
    val location: String?
)