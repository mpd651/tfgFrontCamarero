package com.example.tfgfrontcamarero.data.network


import com.example.tfgfrontcamarero.data.dto.ProductosPedidoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProductosPedidoApiClient {
    @GET("productosPedido")
    @Headers("Accept: application/json")
    suspend fun getProductosPedidoByPedidoId(@Query("pedidoId") pedidoId: Long): Response<List<ProductosPedidoDto>>
}