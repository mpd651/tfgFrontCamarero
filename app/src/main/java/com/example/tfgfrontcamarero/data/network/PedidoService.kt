package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.core.RetrofitHelper
import com.example.tfgfrontcamarero.data.dto.PedidoDto
import com.example.tfgfrontcamarero.data.model.Pedido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PedidoService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getPedidoByMesaId(mesaId:Long): PedidoDto? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PedidoApiClient::class.java).getPedidoByMesaId(mesaId)
            response.body() ?: null
        }
    }

    suspend fun getPedidoById(id:Long): PedidoDto? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PedidoApiClient::class.java).getPedidoById(id)
            response.body() ?: null
        }
    }

    suspend fun getPedidosActivos(): List<PedidoDto> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PedidoApiClient::class.java).getPedidosActivos()
            response.body() ?: emptyList<PedidoDto>()
        }
    }

    suspend fun crearPedido(pedido: Pedido, usuarioId:Long): Long? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PedidoApiClient::class.java).crearPedido(usuarioId, pedido)
            response.body()
        }
    }

    suspend fun pagar(pedido: Pedido, usuarioId:Long) {
        return withContext(Dispatchers.IO) {
            retrofit.create(PedidoApiClient::class.java).pagar(usuarioId, pedido)
        }
    }

    suspend fun anular(usuarioId:Long, pedidoId:Long) {
        return withContext(Dispatchers.IO) {
            retrofit.create(PedidoApiClient::class.java).anular(usuarioId, pedidoId)
        }
    }

    suspend fun asignarPedido(pedidoId:Long) {
        return withContext(Dispatchers.IO) {
            retrofit.create(PedidoApiClient::class.java).asignarPedido(pedidoId)
        }
    }

    suspend fun actualizarPedido(pedido:Pedido, usuarioId:Long): Long? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PedidoApiClient::class.java).actualizarPedido(usuarioId, pedido.id!!, pedido)
            response.body()
        }
    }
}