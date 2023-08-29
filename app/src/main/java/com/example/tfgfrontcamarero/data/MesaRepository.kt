package com.example.tfgfrontcamarero.data

import com.example.tfgfrontcamarero.data.model.Mesa
import com.example.tfgfrontcamarero.data.network.MesaService

class MesaRepository {
    private val api = MesaService()

    suspend fun getAllMesas():List<Mesa>{
        val response = api.getMesas()
        return response
    }
}