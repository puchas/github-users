package pl.puchalski.githubusers.list.viewmodel

import io.reactivex.subjects.PublishSubject
import pl.puchalski.githubusers.common.BaseViewModel

class UserListViewModel : BaseViewModel() {

    private val subject = PublishSubject.create<String>()


    fun search(searchText: String) {


    }
}