package com.bangkit.raionlearncompose2.data.remote

import com.bangkit.raionlearncompose2.data.remote.response.BreweriesResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("breweries")
    suspend fun getBreweries(): Response<BreweriesResponseItem>

    companion object {
        const val BASE_URL = "https://api.openbrewerydb.org/v1/"
    }
}