package com.debend.retrofit_example

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class PostViewModel(context: Context) : ViewModel() {
    private val postList = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = postList

    init {
        postList.value = emptyList()
        CoroutineScope(MainScope().coroutineContext).launch {
            if (isInternetAvailable(context)) {
                val posts = fetchPostsFromServer().getOrElse { emptyList() }
                postList.value = postList.value?.plus(posts)
            }
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context, android.Manifest.permission.INTERNET
            ) == PackageManager.PERMISSION_DENIED
        ) return false

        val connectivityManager =
            getSystemService(context, ConnectivityManager::class.java) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private suspend fun fetchPostsFromServer(): Result<List<Post>> {
        return try {
            val response = RetrofitClient.apiService.getPosts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}