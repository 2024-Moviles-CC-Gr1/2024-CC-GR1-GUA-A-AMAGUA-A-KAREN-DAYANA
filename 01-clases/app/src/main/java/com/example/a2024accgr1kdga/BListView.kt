package com.example.a2024accgr1kdga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, //layout xml a usar
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view
        )
        botonAnadirListView.setOnClickListener{
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)
    }
    // SE ACABA EL ON CREATE
    var posicionItemSeleccionado = -1
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(
        item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                mostrarSnackbar("Editar $posicionItemSeleccionado")
                return true
            }
            R.id.mi_eliminar ->{
                mostrarSnackbar("Eliminar $posicionItemSeleccionado")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun anadirEntrenador(adaptador:ArrayAdapter<BEntrenador>){
        arreglo.add(
            BEntrenador(4, "Wendy","w@w.com")
        )
        adaptador.notifyDataSetChanged()
    }
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}