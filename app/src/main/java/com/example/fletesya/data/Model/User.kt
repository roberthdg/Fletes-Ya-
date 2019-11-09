package com.example.fletesya.data.Model

import java.util.*

data class User(
    val id_usuario: Int,
    val pw: String,
    val correo: String,
    val tipo: String,
    val status: Int,
    val notificacion: Int,
    val verificado: Int,
    val fecha_registro: Date,
    val nombres: String,
    val apellidos: String
)