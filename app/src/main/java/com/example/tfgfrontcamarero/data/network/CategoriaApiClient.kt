package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.data.model.Categoria
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoriaApiClient {
    @GET("categoria")
    @Headers("Accept: application/json")
    suspend fun getAllCategorias(): Response<List<Categoria>>
}