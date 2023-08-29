package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.core.RetrofitHelper
import com.example.tfgfrontcamarero.data.model.Categoria
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriaService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllCategorias(): List<Categoria> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CategoriaApiClient::class.java).getAllCategorias()
            response.body() ?: emptyList()
        }
    }
}