package com.example.tfgfrontcamarero.data.network


import com.example.tfgfrontcamarero.core.RetrofitHelper
import com.example.tfgfrontcamarero.data.model.Mesa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MesaService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getMesas(): List<Mesa> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MesaApiClient::class.java).getAllMesas()
            response.body() ?: emptyList()
        }
    }

}