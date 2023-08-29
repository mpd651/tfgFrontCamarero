package com.example.tfgfrontcamarero.data.model

import java.io.Serializable

data class Producto(
    var id:Long? =  null,
    var nombre:String? =  null,
    var precio:Float? =  null,
    var categoria: Categoria? =  null,
    var productosPedido:List<ProductosPedido>? =  null,
    var borrado:Boolean? =  null
) : Serializable
