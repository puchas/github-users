package pl.puchalski.githubusers.common.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

    return (activeNetwork != null && activeNetwork.isConnected)
}