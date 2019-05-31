package pl.puchalski.githubusers.retrofit.repository

import io.reactivex.Observable
import pl.puchalski.githubusers.common.repository.UserRepository
import pl.puchalski.githubusers.model.User
import pl.puchalski.githubusers.model.UserDetails
import pl.puchalski.githubusers.retrofit.api.UserApi

class RetrofitUserRepository(private val service: UserApi) : UserRepository {

    override fun searchUsersByLogin(login: String): Observable<List<User>> {

        val query = "$login in:login"

        return service.searchUsers(query).flatMap {
            Observable.fromIterable(it.users)
                .map { dto -> User(dto.login) }
                .toList()
                .toObservable()
        }
    }

    override fun getUserDetails(login: String): Observable<UserDetails> {

        return service.getUserDetails(login).map {
            it.run {
                UserDetails(login, avatarUrl, name, blog, location)
            }
        }
    }

}