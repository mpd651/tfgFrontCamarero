package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.data.dto.PedidoDto
import com.example.tfgfrontcamarero.data.model.Pedido
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PedidoApiClient {

    @GET("pedido/mesa")
    @Headers("Accept: application/json")
    suspend fun getPedidoByMesaId(@Query("mesaId") mesaId: Long): Response<PedidoDto>

    @GET("pedido/id")
    @Headers("Accept: application/json")
    suspend fun getPedidoById(@Query("id") id: Long): Response<PedidoDto>

    @GET("pedido/list/clientes")
    @Headers("Accept: application/json")
    suspend fun getPedidosActivos(): Response<List<PedidoDto>>

    @POST("pedido/crear/usuarioId")
    @Headers("Accept: application/json")
    suspend fun crearPedido(@Query("usuarioId") usuarioId: Long, @Body postData: Pedido): Response<Long>

    @PUT("pedido/pagarPedido/usuarioId")
    @Headers("Accept: application/json")
    suspend fun pagar(@Query("usuarioId") usuarioId: Long, @Body postData: Pedido)

    @PUT("pedido/anularPedido/usuarioId/pedidoId")
    @Headers("Accept: application/json")
    suspend fun anular(@Query("usuarioId") usuarioId: Long, @Query("pedidoId") pedidoId: Long)

    @PUT("pedido/pedidoId/asignarCamarero")
    @Headers("Accept: application/json")
    suspend fun asignarPedido(@Query("pedidoId") pedidoId: Long)

    @PUT("pedido/actualizarPedido/usuarioId/pedidoId")
    @Headers("Accept: application/json")
    suspend fun actualizarPedido(@Query("usuarioId") usuarioId: Long, @Query("pedidoId") pedidoId: Long, @Body postData: Pedido): Response<Long>
}