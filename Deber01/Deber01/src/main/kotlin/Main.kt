import java.io.*
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val sistemasSolares = cargarSistemasSolares("sistemas_solares.txt")
    val planetas = cargarPlanetas("planetas.txt")

    loop@ while (true) {
        println("\n--- Menú Principal ---")
        println("1. Crear")
        println("2. Leer")
        println("3. Actualizar")
        println("4. Eliminar")
        println("5. Salir")
        print("Ingrese su opción: ")

        when (scanner.nextInt()) {
            1 -> submenuCrear(sistemasSolares, planetas, scanner)
            2 -> submenuLeer(sistemasSolares, planetas, scanner)
            3 -> submenuActualizar(sistemasSolares, planetas, scanner)
            4 -> submenuEliminar(sistemasSolares, planetas, scanner)
            5 -> {
                guardarSistemasSolares(sistemasSolares, "sistemas_solares.txt")
                guardarPlanetas(planetas, "planetas.txt")
                break@loop
            }
            else -> println("Opción no válida. Intente de nuevo.")
        }
    }

    println("¡Programa finalizado!")
}


fun cargarSistemasSolares(nombreArchivo: String): MutableList<SistemaSolar> {
    val file = File(nombreArchivo)
    if (file.length() == 0L) {
        return mutableListOf()
    }

    return try {
        ObjectInputStream(FileInputStream(file)).use { stream ->
            stream.readObject() as MutableList<SistemaSolar>
        }
    } catch (e: Exception) {
        e.printStackTrace()
        mutableListOf()
    }
}

fun guardarSistemasSolares(sistemasSolares: MutableList<SistemaSolar>, nombreArchivo: String) {
    try {
        ObjectOutputStream(FileOutputStream(nombreArchivo)).use { stream ->
            stream.writeObject(sistemasSolares)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun cargarPlanetas(nombreArchivo: String): MutableList<Planeta> {
    val file = File(nombreArchivo)
    if (file.length() == 0L) {
        return mutableListOf()
    }

    return try {
        ObjectInputStream(FileInputStream(file)).use { stream ->
            stream.readObject() as MutableList<Planeta>
        }
    } catch (e: Exception) {
        e.printStackTrace()
        mutableListOf()
    }
}

fun guardarPlanetas(planetas: MutableList<Planeta>, nombreArchivo: String) {
    try {
        ObjectOutputStream(FileOutputStream(nombreArchivo)).use { stream ->
            stream.writeObject(planetas)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


//-------------SUBMENUS------------------
fun submenuCrear(sistemasSolares: MutableList<SistemaSolar>, planetas: MutableList<Planeta>, scanner: Scanner) {
    println("\n--- Submenú Crear ---")
    println("1. Crear Planeta")
    println("2. Crear Sistema Solar")
    print("Ingrese su opción: ")

    when (scanner.nextInt()) {
        1 -> {
            crearPlaneta(planetas, scanner)
        }
        2 -> {
            val nuevoSistemaSolar = crearSistemaSolar(sistemasSolares, planetas, scanner)
            sistemasSolares.add(nuevoSistemaSolar)
            println("Sistema Solar creado con éxito.")
        }
        else -> println("Opción no válida. Intente de nuevo.")
    }
}

fun submenuLeer(sistemasSolares: MutableList<SistemaSolar>, planetas: MutableList<Planeta>, scanner: Scanner) {
    println("\n--- Submenú Leer Información ---")
    println("1. Leer Información de un Sistema Solar")
    println("2. Leer Información de Todos los Planetas")
    print("Ingrese su opción: ")

    when (scanner.nextInt()) {
        1 -> {
            println("\n--- Submenú Leer Información de un Sistema Solar ---")
            println("Seleccione un Sistema Solar:")
            imprimirNombresSistemasSolares(sistemasSolares)

            print("Ingrese el índice del Sistema Solar a leer: ")
            val indiceSistemaSolar = scanner.nextInt()

            if (indiceSistemaSolar in 0 until sistemasSolares.size) {
                val sistemaSolarSeleccionado = sistemasSolares[indiceSistemaSolar]
                imprimirInformacionSistemaSolar(sistemaSolarSeleccionado)
            } else {
                println("Índice no válido. No se realizó ninguna lectura.")
            }
        }
        2 -> {
            println("\n--- Submenú Leer Información de un Planeta ---")
            println("Seleccione un Planeta:")
            imprimirNombresPlanetas(planetas)

            print("Ingrese el índice del Planeta a leer: ")
            val indicePlaneta = scanner.nextInt()

            if (indicePlaneta in 0 until planetas.size) {
                val planetaSeleccionado = planetas[indicePlaneta]
                imprimirInformacionPlaneta(planetaSeleccionado)
            } else {
                println("Índice no válido. No se realizó ninguna lectura.")
            }
        }
        else -> println("Opción no válida. Intente de nuevo.")
    }
}

fun submenuActualizar(sistemasSolares: MutableList<SistemaSolar>, planetas: MutableList<Planeta>, scanner: Scanner) {
    println("\n--- Submenú Actualizar ---")
    println("1. Actualizar Información de un Planeta")
    println("2. Actualizar Información de un Sistema Solar")
    print("Ingrese su opción: ")

    when (scanner.nextInt()) {
        1 -> {
            println("\n--- Submenú Actualizar Información de un Planeta ---")
            println("Seleccione un planeta para actualizar:")
            imprimirNombresPlanetas(planetas)

            print("Ingrese el índice del planeta a actualizar: ")
            val indicePlaneta = scanner.nextInt()

            if (indicePlaneta in 0 until planetas.size) {
                val planetaSeleccionado = planetas[indicePlaneta]
                actualizarInformacionPlaneta(planetaSeleccionado, scanner)
            } else {
                println("Índice no válido. No se realizó ninguna actualización.")
            }
        }
        2 -> {
            println("\n--- Submenú Actualizar Información de un Sistema Solar ---")
            println("Seleccione un Sistema Solar para actualizar:")
            imprimirNombresSistemasSolares(sistemasSolares)

            print("Ingrese el índice del Sistema Solar a actualizar: ")
            val indiceSistemaSolar = scanner.nextInt()

            if (indiceSistemaSolar in 0 until sistemasSolares.size) {
                val sistemaSolarSeleccionado = sistemasSolares[indiceSistemaSolar]
                actualizarInformacionSistemaSolar(sistemaSolarSeleccionado, planetas, scanner)
            } else {
                println("Índice no válido. No se realizó ninguna actualización.")
            }
        }
        else -> println("Opción no válida. Intente de nuevo.")
    }
}

fun submenuEliminar(sistemasSolares: MutableList<SistemaSolar>, planetas: MutableList<Planeta>, scanner: Scanner) {
    println("\n--- Submenú Eliminar ---")
    println("1. Eliminar Planeta")
    println("2. Eliminar Sistema Solar")
    print("Ingrese su opción: ")

    when (scanner.nextInt()) {
        1 -> {
            println("\n--- Submenú Eliminar Planeta ---")
            println("Seleccione un planeta para eliminar:")
            imprimirNombresPlanetas(planetas)

            print("Ingrese el índice del planeta a eliminar: ")
            val indicePlaneta = scanner.nextInt()

            if (indicePlaneta in 0 until planetas.size) {
                eliminarPlaneta(planetas, indicePlaneta)
                println("Planeta eliminado con éxito.")
            } else {
                println("Índice no válido. No se realizó ninguna eliminación.")
            }
        }

        2 -> {
            println("\n--- Submenú Eliminar Sistema Solar ---")
            println("Seleccione un Sistema Solar para eliminar:")
            imprimirNombresSistemasSolares(sistemasSolares)

            print("Ingrese el índice del Sistema Solar a eliminar: ")
            val indiceSistemaSolar = scanner.nextInt()

            if (indiceSistemaSolar in 0 until sistemasSolares.size) {
                eliminarSistemaSolar(sistemasSolares, indiceSistemaSolar)
                println("Sistema Solar eliminado con éxito.")
            } else {
                println("Índice no válido. No se realizó ninguna eliminación.")
            }
        }

        else -> println("Opción no válida. Intente de nuevo.")
    }
}

//----------------------CRUD-----------------------------------

//CREATE

fun crearSistemaSolar(sistemasSolares: MutableList<SistemaSolar>, planetas: MutableList<Planeta>, scanner: Scanner): SistemaSolar {
    println("\n--- Crear Sistema Solar ---")
    print("Nombre: ")
    val nombre = scanner.next()
    print("fecha descubrimiento:  (yyyy-MM-dd)")
    val fechaDescubrimientoStr = scanner.next()
    val fechaDescubrimiento = SimpleDateFormat("yyyy-MM-dd").parse(fechaDescubrimientoStr)
    print("Distancia centro galaxia: ")
    val distanciaCentroGalaxia = scanner.nextDouble()
    print("Numero estrellas")
    val numeroEstrellas = scanner.nextInt()
    print("Es habitable?")
    val esHabitable = scanner.nextBoolean()

    // Mostrar lista de planetas disponibles
    println("\nPlanetas Disponibles:")
    imprimirNombresPlanetas(planetas)

    print("Ingrese los nombres de los planetas que desea agregar (separados por comas): ")
    val planetasSeleccionados = scanner.next().split(",").map { it.trim() }

    // Filtrar los planetas seleccionados de la lista completa
    val planetasAAgregar = planetas.filter { it.nombre in planetasSeleccionados }.toMutableList()

    // Crear el nuevo sistema solar con los planetas seleccionados
    val nuevoSistemaSolar = SistemaSolar(nombre, fechaDescubrimiento, distanciaCentroGalaxia, numeroEstrellas,esHabitable, planetasAAgregar)

    return nuevoSistemaSolar
}

fun imprimirNombresPlanetas(planetas: MutableList<Planeta>) {
    planetas.forEachIndexed { index, planeta ->
        println("$index. ${planeta.nombre}")
    }
}

fun crearPlaneta(planetas: MutableList<Planeta>, scanner: Scanner) {
    println("\n--- Crear Planeta ---")
    print("Nombre: ")
    val nombre = scanner.next()
    print("Diámetro (en kilómetros): ")
    val diametro = scanner.nextDouble()
    print("Masa (en kg): ")
    val masa = scanner.nextDouble()
    print("Distancia al Sol (en millones de km): ")
    val distanciaAlSol = scanner.nextDouble()
    print("Tiene aro True o false: ")
    val atmosfera = scanner.nextBoolean()

    // Crear nuevo planeta y agregarlo a la lista
    val nuevoPlaneta = Planeta(nombre, diametro, masa, distanciaAlSol, atmosfera)
    planetas.add(nuevoPlaneta)

    println("Planeta creado con éxito.")
}

//READ
fun imprimirNombresSistemasSolares(sistemasSolares: MutableList<SistemaSolar>) {
    sistemasSolares.forEachIndexed { index, sistemaSolar ->
        println("$index. ${sistemaSolar.nombre}")
    }
}

fun imprimirInformacionSistemaSolar(sistemaSolar: SistemaSolar) {
    println("\n--- Información del Sistema Solar ---")
    println("Nombre: ${sistemaSolar.nombre}")
    println("Fecha descubrimiento: ${sistemaSolar.fechaDescubrimiento} ")
    println("Distancia centro galaxia: ${sistemaSolar.distanciaCentroGalaxia}")
    println("Nº de Estrella: ${sistemaSolar.numeroEstrellas}")
    println("Es habitable?: ${sistemaSolar.esHabitable}")



    if (sistemaSolar.planetas.isNotEmpty()) {
        println("\n--- Planetas ---")
        sistemaSolar.planetas.forEachIndexed { index, planeta ->
            println("$index. ${planeta.nombre} - Diámetro: ${planeta.diametro} ")
        }
    } else {
        println("\nNo hay planetas en este sistema solar.")
    }
}

fun imprimirInformacionPlaneta(planeta: Planeta) {
    println("--- Información del Planeta ---")
    println("Nombre: ${planeta.nombre}")
    println("Diámetro: ${planeta.diametro} km")
    println("Masa: ${planeta.masa} kg")
    println("Distancia al Sol: ${planeta.distanciaSol} millones de km")
    println("tiene lunas: ${planeta.tieneAro}")
}

//UPDATE
fun actualizarInformacionPlaneta(planeta: Planeta, scanner: Scanner) {
    println("\n--- Actualizar Información de un Planeta ---")
    imprimirInformacionPlaneta(planeta)

    println("\nIngrese los nuevos datos para el planeta:")

    print("Nuevo nombre: ")
    planeta.nombre = scanner.next()

    print("Nuevo diámetro (km): ")
    planeta.diametro = scanner.nextDouble()

    print("Nueva masa (kg): ")
    planeta.masa = scanner.nextDouble()

    print("Nueva distancia al Sol (millones de km): ")
    planeta.distanciaSol = scanner.nextDouble()

    print("Nuevo aro: ")
    planeta.tieneAro = scanner.nextBoolean()

    println("Información del planeta actualizada con éxito.")
}

fun actualizarInformacionSistemaSolar(sistemaSolar: SistemaSolar, planetas: MutableList<Planeta>, scanner: Scanner) {
    println("\n--- Actualizar Información del Sistema Solar ---")
    println("Sistema Solar actual:")
    imprimirInformacionSistemaSolar(sistemaSolar)

    print("\nIngrese el nuevo nombre: ")
    val nuevoNombre = scanner.next()
    sistemaSolar.nombre = nuevoNombre

    print("\nIngrese fechaDescubrimiento: ")
    val nuevaFechaStr = scanner.next()
    val nuevafechaDescubrimiento = SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaStr)
    sistemaSolar.fechaDescubrimiento = nuevafechaDescubrimiento

    print("Ingrese la distanciaCentroGalaxia: ")
    val nuevadistanciaCentroGalaxia = scanner.nextDouble()
    sistemaSolar.distanciaCentroGalaxia = nuevadistanciaCentroGalaxia

    print("Ingrese el nuevo numeroEstrellas: ")
    val nuevonumeroEstrellas= scanner.nextInt()
    sistemaSolar.numeroEstrellas = nuevonumeroEstrellas

    print("Ingrese el nuevo esHabitable: ")
    val nuevoesHabitable= scanner.nextBoolean()
    sistemaSolar.esHabitable = nuevoesHabitable


    println("\nPlanetas Disponibles:")
    imprimirNombresPlanetas(planetas)
    print("Ingrese los nuevos planetas (separados por comas): ")

    val planetasSeleccionados = scanner.next().split(",").map { it.trim() }
    val planetasAAgregar = planetas.filter { it.nombre in planetasSeleccionados }
    sistemaSolar.planetas = planetasAAgregar.toMutableList()

    println("Sistema Solar actualizado con éxito.")
}

//DELETE
fun eliminarPlaneta(planetas: MutableList<Planeta>, indice: Int) {
    // Implementa la lógica para eliminar el planeta en el índice proporcionado
    planetas.removeAt(indice)
}

fun eliminarSistemaSolar(sistemasSolares: MutableList<SistemaSolar>, indice: Int) {
    // Implementa la lógica para eliminar el sistema solar en el índice proporcionado
    sistemasSolares.removeAt(indice)
}
