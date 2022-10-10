package com.example.prodan.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class pets (
    val id: String,
    val nombre: String,
    val imagen : String,
    val edad : String,
    val sexo : Int,
    val especie : String,
    val descripcion : String,
    val raza : String
) : Parcelable