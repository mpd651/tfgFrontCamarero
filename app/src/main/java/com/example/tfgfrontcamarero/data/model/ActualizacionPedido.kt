package com.example.tfgfrontcamarero.data.model

import java.io.Serializable
import java.time.LocalDateTime

data class ActualizacionPedido(
    val id:Long,
    val pedido: Pedido,
    val usuario: Usuario,
    val fecha: String,
    val motivo: String
):Serializable
