package pl.puchalski.githubusers.retrofit.module

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.dsl.module
import pl.puchalski.githubusers.common.extension.isNetworkAvailable
import pl.puchalski.githubusers.retrofit.api.UserApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"
private const val cacheSize = (5 * 1024 * 1024).toLong()

val retrofitModule = module {
    single { createOkHttpClient(get(), cacheSize) }
    single<UserApi> { createWebService(get(), BASE_URL) }
}

fun createOkHttpClient(context: Context, cacheSize: Long): OkHttpClient = OkHttpClient.Builder()
    .cache(Cache(context.cacheDir, cacheSize))
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

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}
