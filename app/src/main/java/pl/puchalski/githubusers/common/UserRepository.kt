package pl.puchalski.githubusers.common

import io.reactivex.Observable
import pl.puchalski.githubusers.model.User
import pl.puchalski.githubusers.retrofit.RetrofitClient
import pl.puchalski.githubusers.retrofit.api.UserApi

class UserRepository {

    private val service: UserApi = RetrofitClient.createService()

    fun searchUsers(login: String): Observable<List<User>> {

        val query = "$login in:login"

        return service.searchUsers(query).flatMap {
            Observable.fromIterable(it.users)
                .map { dto -> User(dto.login) }
                .toList()
                .toObservable()
        }
    }

}