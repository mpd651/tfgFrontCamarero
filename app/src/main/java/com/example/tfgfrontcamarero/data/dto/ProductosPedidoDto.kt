package com.example.tfgfrontcamarero.data.dto


data class ProductosPedidoDto(
    var productoPedidoid:Long?=null,
    var productoId:Long?=null,
    var cantidad: Int?=null,
    var hora: String?=null,
    var productoNombre: String?=null,
    var productoPrecio: Float?=null
)
