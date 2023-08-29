package com.example.tfgfrontcamarero.data.dto

import com.example.tfgfrontcamarero.data.model.Mesa
import java.io.Serializable

data class PedidoDto(
    val id:Long,
    var mesa: Mesa,
    var importe:Float,
    var fechaApertura: String,
    var fechaCierre: String,
    var pagado:Boolean,
    var anulado:Boolean,
    var asignadoCamarero:Boolean
): Serializable
