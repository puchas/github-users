package pl.puchalski.githubusers.retrofit.repository

import android.content.Context
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import pl.puchalski.githubusers.common.extension.isNetworkAvailable
import pl.puchalski.githubusers.common.repository.UserRepository
import pl.puchalski.githubusers.model.User
import pl.puchalski.githubusers.model.UserDetails
import pl.puchalski.githubusers.retrofit.api.UserApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUserRepository(context: Context) : UserRepository {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(context.cacheDir, cacheSize)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (context.isNetworkAvailable()) {
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            } else {
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            }

            chain.proceed(request)
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    private val service = retrofit.create(UserApi::class.java)

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

    override fun isRemoteRepository(): Boolean = true

}