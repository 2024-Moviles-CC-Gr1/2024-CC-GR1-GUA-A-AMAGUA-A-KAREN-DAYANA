import java.time.LocalDate
import java.io.Serializable
import java.util.Date

data class SistemaSolar(
    var nombre: String,
    var fechaDescubrimiento: Date,
    var distanciaCentroGalaxia: Double,
    var numeroEstrellas: Int,
    var esHabitable: Boolean,
    var planetas: MutableList<Planeta> = mutableListOf()
) : Serializable
