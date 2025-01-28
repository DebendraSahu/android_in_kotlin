package com.debend.apihandling

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    // For Callback Approach
    @GET("nonexistent-endpoint")
    fun getNonExistentDataWithCallback(): Call<String>

    // For Coroutine Approach
    @GET("nonexistent-endpoint")
    suspend fun getNonExistentDataWithCoroutine(): Response<String>
}
