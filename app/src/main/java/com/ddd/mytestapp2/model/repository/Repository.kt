package com.ddd.mytestapp2.model.repository

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.ddd.mytestapp2.model.api.RetrofitInstance
import com.ddd.mytestapp2.model.modelFood.ModelAnswer
import retrofit2.Response

class Repository {

    suspend fun getListFood(query:String): Response<ModelAnswer> {
        return RetrofitInstance.api.getListFood(query)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun checkInternet(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnected ?: false
        }
    }

}