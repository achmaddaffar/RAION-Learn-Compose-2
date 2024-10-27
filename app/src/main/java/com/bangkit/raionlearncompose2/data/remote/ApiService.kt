package com.bangkit.raionlearncompose2.data.remote

import com.bangkit.raionlearncompose2.data.remote.response.ProductResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    suspend fun getProducts(): Response<List<ProductResponseItem>>

    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") id: String
    ): Response<ProductResponseItem>

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
    }
}