package com.example.tfgfrontcamarero.data.network

import com.example.tfgfrontcamarero.data.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsuarioApiClient {
    @GET("usuario/lista?rol=ADMINISTRADOR")
    suspend fun getAllUsuarios(): Response<List<Usuario>>

    @POST("usuario/login")
    @Headers("Accept: application/json")
    suspend fun login(@Body postData: Usuario): Response<Usuario>

    @POST("usuario/registrar")
    @Headers("Accept: application/json")
    suspend fun registrar(@Body postData: Usuario): Response<Usuario>

    @POST("usuario/registrarPassword")
    @Headers("Accept: application/json")
    suspend fun registrarPassword(@Body postData: Usuario): Response<Usuario>

}