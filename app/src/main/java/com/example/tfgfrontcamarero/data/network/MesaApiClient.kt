package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.data.model.Mesa
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MesaApiClient {

    @GET("mesa")
    @Headers("Accept: application/json")
    suspend fun getAllMesas(): Response<List<Mesa>>
}