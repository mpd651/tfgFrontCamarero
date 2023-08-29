package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.core.RetrofitHelper
import com.example.tfgfrontcamarero.data.dto.ProductosPedidoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductosPedidoService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getProductosPedidoByPedidoId(pedidoId:Long): List<ProductosPedidoDto> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductosPedidoApiClient::class.java).getProductosPedidoByPedidoId(pedidoId)
            response.body() ?: emptyList()
        }
    }
}