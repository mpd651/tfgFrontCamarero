package com.example.tfgfrontcamarero.data

import com.example.tfgfrontcamarero.data.dto.PedidoDto
import com.example.tfgfrontcamarero.data.model.Pedido
import com.example.tfgfrontcamarero.data.network.PedidoService

class PedidoRepository {
    private val api = PedidoService()

    suspend fun getPedidoByMesaId(mesaId:Long): PedidoDto? {
        val response = api.getPedidoByMesaId(mesaId)
        return response
    }

    suspend fun getPedidoById(id:Long): PedidoDto? {
        val response = api.getPedidoById(id)
        return response
    }

    suspend fun getPedidosActivos(): List<PedidoDto> {
        val response = api.getPedidosActivos()
        return response
    }

    suspend fun crearPedido(pedido: Pedido, usuarioId:Long): Long? {
        val response = api.crearPedido(pedido, usuarioId)
        return response
    }

    suspend fun anular(usuarioId:Long, pedidoId:Long) {
        api.anular(usuarioId, pedidoId)
    }

    suspend fun asignarPedido(pedidoId:Long) {
        api.asignarPedido(pedidoId)
    }

    suspend fun pagar(usuarioId:Long, pedido: Pedido) {
        api.pagar(pedido, usuarioId)
    }

    suspend fun actualizarPedido(pedido:Pedido, usuarioId:Long): Long? {
        val response = api.actualizarPedido(pedido, usuarioId)
        return response
    }
}