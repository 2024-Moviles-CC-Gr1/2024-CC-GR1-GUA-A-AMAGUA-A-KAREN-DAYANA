package com.example.examenb1_karen_guana

import java.util.Date

class SistemaOperativo (
    var id: Int,
    var nombre: String,
    var version: String,
    var fechaLanzamiento: Date,
    var esGratis: Boolean,
    var desarrollador: String
){
    override fun toString(): String {
                            return "$nombre - $version"
    }
}