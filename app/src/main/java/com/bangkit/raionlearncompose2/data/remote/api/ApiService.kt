package com.bangkit.raionlearncompose2.data.remote.api

import com.bangkit.raionlearncompose2.data.remote.response.ProductResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getBreweries(): Response<List<ProductResponseItem>>

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
    }
}