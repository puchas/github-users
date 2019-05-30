package pl.puchalski.githubusers.common.repository

import io.reactivex.Observable
import pl.puchalski.githubusers.model.User
import pl.puchalski.githubusers.model.UserDetails

interface UserRepository {
    fun searchUsersByLogin(login: String): Observable<List<User>>
    fun getUserDetails(login: String): Observable<UserDetails>
    fun isRemoteRepository(): Boolean
}