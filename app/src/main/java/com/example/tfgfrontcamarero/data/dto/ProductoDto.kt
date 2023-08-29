package com.example.tfgfrontcamarero.data.dto

import com.example.tfgfrontcamarero.data.model.Categoria

data class ProductoDto(
    val id:Long,
    val nombre:String,
    val precio:Float,
    val categoria: Categoria,
    val borrado:Boolean
)
