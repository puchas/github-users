package pl.puchalski.githubusers.common.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pl.puchalski.githubusers.common.repository.UserRepository
import pl.puchalski.githubusers.details.viewmodel.UserDetailsViewModel
import pl.puchalski.githubusers.list.viewmodel.UserListViewModel
import pl.puchalski.githubusers.retrofit.repository.RetrofitUserRepository

class BaseApplication : Application() {

    private val appModule = module {
        single<UserRepository> { RetrofitUserRepository() }
        viewModel { UserListViewModel(get(), get()) }
        viewModel { UserDetailsViewModel(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}