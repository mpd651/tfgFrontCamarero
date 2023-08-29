package com.example.tfgfrontcamarero.data.model

import java.io.Serializable
import java.time.LocalDateTime

data class ProductosPedido(
    var productoPedidoid:Long?=null,
    var producto: Producto?=null,
    var pedido: Pedido?=null,
    var cantidad: Int?=null,
    var hora: String?=null
): Serializable
