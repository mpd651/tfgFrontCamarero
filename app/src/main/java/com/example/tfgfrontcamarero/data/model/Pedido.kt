package com.example.tfgfrontcamarero.data.model

import java.io.Serializable
import java.time.LocalDateTime

data class Pedido(
    val id:Long? = null,
    var mesa: Mesa? = null,
    var actualizaciones:List<ActualizacionPedido>? = null,
    var productosPedidos:List<ProductosPedido>? = null,
    var importe:Float? = null,
    var fechaApertura: String? = null,
    var fechaCierre: String? = null,
    var pagado:Boolean? = null,
    var anulado:Boolean? = null,
    var asignadoCamarero:Boolean? = null
): Serializable
