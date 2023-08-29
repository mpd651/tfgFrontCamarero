package com.example.tfgfrontcamarero.data

import com.example.tfgfrontcamarero.data.dto.ProductoDto
import com.example.tfgfrontcamarero.data.network.ProductoService

class ProductoRepository {
    private val api = ProductoService()

    suspend fun getProductosByCategoria(categoriaId:Long): List<ProductoDto> {
        val response = api.getProductosByCategoria(categoriaId)
        return response
    }
}