package pl.puchalski.githubusers.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import pl.puchalski.githubusers.common.BaseViewModel
import pl.puchalski.githubusers.common.UserRepository
import pl.puchalski.githubusers.model.User
import java.util.concurrent.TimeUnit

class UserListViewModel : BaseViewModel() {

    val usersData = MutableLiveData<List<User>>()

    private val repo = UserRepository()
    private val subject = PublishSubject.create<String>()

    fun search(login: String) {

        subject
            .filter { it.isNotEmpty() }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap { it ->
                repo.searchUsers(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                //usersData.postValue(DataState.Loading())
            }.subscribe(
                {
                    //usersData.postValue(DataState.Loaded(it))
                    usersData.postValue(it)
                },
                {
                    //usersData.postValue(DataState.Error("Wystąpił błąd podczas pobierania danych"))
                    Log.e(this::class.java.simpleName, it.localizedMessage, it)
                }
            ).let {
                addToCompositeDisposable(it)
            }

        subject.onNext(login)
    }
}