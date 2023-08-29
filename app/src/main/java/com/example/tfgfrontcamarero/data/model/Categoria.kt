package com.example.tfgfrontcamarero.data.model

import java.io.Serializable

data class Categoria(
    val id:Long,
    val nombre:String,
    val borrado:Boolean
):Serializable
