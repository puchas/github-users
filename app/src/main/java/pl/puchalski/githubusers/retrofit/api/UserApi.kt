package pl.puchalski.githubusers.retrofit.api

import io.reactivex.Observable
import pl.puchalski.githubusers.retrofit.dto.UserDetailsDto
import pl.puchalski.githubusers.retrofit.dto.UserListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("search/users")
    fun searchUsers(@Query("q") userQuery: String): Observable<UserListDto>

    @GET("users/{login}")
    fun getUserDetails(@Path("login") login: String): Observable<UserDetailsDto>
}