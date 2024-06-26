package com.example.a2024accgr1kdga

class BBaseDatosMemoria {
    companion object{

        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Adrian","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Vicente","bab@.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carolina","c@c.com")
                )
        }
    }
}