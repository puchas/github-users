package pl.puchalski.githubusers.retrofit.api

import io.reactivex.Observable
import pl.puchalski.githubusers.retrofit.dto.UserListDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserApi {
    @Headers("User-Agent: puchas")
    @GET("search/users")
    fun searchUsers(@Query("q") userQuery: String): Observable<UserListDto>
}