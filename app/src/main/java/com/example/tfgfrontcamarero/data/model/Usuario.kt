package com.example.tfgfrontcamarero.data.model

import java.io.Serializable

data class Usuario(
    var id:Long? = null,
    var nombre: String? = null,
    var apellidos: String? = null,
    var userName: String? = null,
    var password: String? = null,
    var borrado: Boolean? = null,
    var rol: String? = null
): Serializable
