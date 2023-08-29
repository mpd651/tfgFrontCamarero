package com.example.tfgfrontcamarero.data

import com.example.tfgfrontcamarero.data.model.Categoria
import com.example.tfgfrontcamarero.data.network.CategoriaService

class CategoriaRepository {
    private val api = CategoriaService()

    suspend fun getAllCategorias():List<Categoria>{
        val response = api.getAllCategorias()
        return response
    }

}