package com.example.a2024accgr1kdga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {
    var textoGlobal = ""

    fun mostrarSnackbar(texto:String){
        textoGlobal+= texto
        val snack= Snackbar.make(
            findViewById(R.id.id_layout_main),
            textoGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostrarSnackbar("OnCreate")
    }
    override fun onStart(){
       super.onStart()
        mostrarSnackbar("onStart")
    }
    override fun onResume(){
        super.onResume()
        mostrarSnackbar("onResume")
    }

    override fun onRestart(){
        super.onRestart()
        mostrarSnackbar("onRestart")
    }

    override fun onPause(){
        super.onPause()
        mostrarSnackbar("onPause")
    }

    override fun onStop(){
        super.onStop()
        mostrarSnackbar("onStop")
    }
    override fun onDestroy(){
        super.onDestroy()
        mostrarSnackbar("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState
            .run {
                putString("varibaleTextoGuardado",textoGlobal)
            }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoRecuperadoDeVariable: String? = savedInstanceState.getString("variableTextoGuardado")
        if (textoRecuperadoDeVariable !=null ){
            mostrarSnackbar(textoRecuperadoDeVariable)
            textoGlobal=textoRecuperadoDeVariable
        }
    }


}