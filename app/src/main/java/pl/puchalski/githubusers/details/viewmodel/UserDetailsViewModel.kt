package pl.puchalski.githubusers.details.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.puchalski.githubusers.common.BaseViewModel
import pl.puchalski.githubusers.common.UserRepository
import pl.puchalski.githubusers.model.UserDetails

class UserDetailsViewModel : BaseViewModel() {

    val user = MutableLiveData<UserDetails>()

    private var storedLogin: String? = null

    private val repo = UserRepository()

    fun getUserDetails(login: String) {

        if (login == storedLogin) {
            return
        }

        storedLogin = login

        repo.getUserDetails(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    user.postValue(it)
                },
                {
                    Log.e(this::class.java.simpleName, it.localizedMessage, it)
                }
            ).let {
                addToCompositeDisposable(it)
            }
    }
}