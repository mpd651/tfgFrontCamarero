package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.data.dto.ProductoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProductoApiClient {
    @GET("producto/categoria/categoriaId")
    @Headers("Accept: application/json")
    suspend fun getProductosByCategoria(@Query("categoriaId") categoriaId: Long): Response<List<ProductoDto>>
}