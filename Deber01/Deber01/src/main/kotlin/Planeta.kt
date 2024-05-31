import java.io.Serializable
// Planeta.kt
data class Planeta(
    var nombre: String,
    var masa: Double,
    var diametro: Double,
    var distanciaSol: Double,
    var tieneAro: Boolean
) : Serializable
