package com.ceduc.comm


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

/*
Pendientes:
[x] Que los datos se actulizen cada vez que inicia la app
[ ] Que los datos de la tabla porducto se muestren en el activity FormProd
[x] Mejorar la muestra de datos en el carrito y sus views
[x] Revisar que cada activity tenga sus views como corresponde
[x] Crear las funcion actualizar datos para las tablas producto y carrito
[x] Crear las funcion borrar datos para las tablas producto y carrito
[ ] Crear los Toast para indicar cuando una accion se realizo
*/

class MainActivity : AppCompatActivity() {
    // Crear la base de datos
    lateinit var database: SQLiteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //iniciar la base de datos
        database = SQLiteDB(this)

        database.borrarDatosCarrito()

        //Variables para recuperar y manipular los views
        val btnDrone = findViewById<ImageButton>(R.id.btnDrone)
        val btnMac = findViewById<ImageButton>(R.id.btnMac)
        val btnEarphone = findViewById<ImageButton>(R.id.btnEarphone)
        val btnVR = findViewById<ImageButton>(R.id.btnVR)
        val btnCarrito = findViewById<Button>(R.id.btnCarrito)
        val btnListar = findViewById<Button>(R.id.btnListar)
        val txtProductos = findViewById<TextView>(R.id.txtProductos)


        //Listener de los boton drone
        btnDrone.setOnClickListener {

            //Intent para cambiar al formulario y enviar los datos iniciales - diferencia entre intent e Intent (CAPS)
            val intent = Intent(this, FormProd::class.java)
            intent.putExtra("clave", "Dron")
            startActivity(intent)
        }

        //Listener del boton Mac
        btnMac.setOnClickListener {

            //Intent para cambiar al formulario y enviar los datos iniciales - diferencia entre intent e Intent (CAPS)
            val intent = Intent(this, FormProd::class.java)
            intent.putExtra("clave", "MacPC")
            startActivity(intent)
        }

        //Listener del boton earphone
        btnEarphone.setOnClickListener {

            //Intent para cambiar al formulario y enviar los datos iniciales - diferencia entre intent e Intent (CAPS)
            val intent = Intent(this, FormProd::class.java)
            intent.putExtra("clave", "Earphone")
            startActivity(intent)
        }

        //Listener del boton VR
        btnVR.setOnClickListener {

            //Intent para cambiar al formulario y enviar los datos iniciales - diferencia entre intent e Intent (CAPS)
            val intent = Intent(this, FormProd::class.java)
            intent.putExtra("clave", "VR")
            startActivity(intent)
        }

        //Listener que muestra la lista de productos
        btnCarrito.setOnClickListener {

            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)
        }

        //Listener para btnListar
        btnListar.setOnClickListener {

            val intent = Intent(this, Listar::class.java)
            startActivity(intent)
        }

    }
}