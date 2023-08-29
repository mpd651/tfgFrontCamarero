package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.core.RetrofitHelper
import com.example.tfgfrontcamarero.data.dto.ProductoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductoService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getProductosByCategoria(categoriaId:Long): List<ProductoDto> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductoApiClient::class.java).getProductosByCategoria(categoriaId)
            response.body() ?: emptyList()
        }
    }
}