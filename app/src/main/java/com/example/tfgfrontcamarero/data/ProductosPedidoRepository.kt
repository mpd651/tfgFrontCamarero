package com.example.tfgfrontcamarero.data

import com.example.tfgfrontcamarero.data.dto.ProductosPedidoDto
import com.example.tfgfrontcamarero.data.network.ProductosPedidoService

class ProductosPedidoRepository {
    private val api = ProductosPedidoService()

    suspend fun getProductosPedidoByPedidoId(pedidoId:Long): List<ProductosPedidoDto> {
        val response = api.getProductosPedidoByPedidoId(pedidoId)
        return response
    }
}