package pl.puchalski.githubusers.list.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.puchalski.githubusers.common.repository.UserRepository
import pl.puchalski.githubusers.common.viewmodel.BaseViewModel
import pl.puchalski.githubusers.model.User


class UserListViewModel(application: Application, private val repo: UserRepository) : BaseViewModel(application) {

    val usersData = MutableLiveData<List<User>>()

    fun search(login: String) {

        repo.searchUsersByLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
            }.subscribe(
                {
                    usersData.postValue(it)
                },
                {
                    Log.e(this::class.java.simpleName, it.localizedMessage, it)
                }
            ).let {
                addToCompositeDisposable(it)
            }

    }
}