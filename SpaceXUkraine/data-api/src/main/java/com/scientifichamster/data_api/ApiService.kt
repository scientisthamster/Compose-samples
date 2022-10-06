package com.scientifichamster.data_api

import com.scientifichamster.data_api.model.CompanyInfoResponse
import com.scientifichamster.data_api.model.LaunchesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("info")
    suspend fun getCompanyInfo(): CompanyInfoResponse

    @GET("launches")
    suspend fun getAllLaunches(): List<LaunchesResponse>
}