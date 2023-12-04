package com.ceduc.comm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Carrito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Iniciar db pero hace referencia a la misma, por eso la llamo databaseAux
        val databaseAux = SQLiteDB(this)

        // Recuperar los views
        val txtCarrito = findViewById<TextView>(R.id.txtCarrito)  // TexView para mostrar los productos
        val btnPagar = findViewById<Button>(R.id.btnPagar)
        val btnBackC = findViewById<Button>(R.id.btnBackC)

        // Mostrar los datos de la tabla carrito
        val listaDatos = databaseAux.mostrarDatosCarrito()

        // Contador para indicar el numero del producto
        var numProd = 0

        // Ciclo for en aumento de 3 que alamcena el valor perteneciente al indice del elemento guardado en listaDatos
        for (i in listaDatos.indices step 3) {
            val code = listaDatos[i]
            val description = listaDatos[i + 1]
            val price = listaDatos[i + 2]

            // Aumentar el comtador
            numProd ++

            // Agregar los valores y dar formato para mostrarlos en el textView
            txtCarrito.append("Producto $numProd\nCódigo: $code\nDescripción: $description\nPrecio: $price\n\n\n")
        }

        // Listener para el boton pagar
        btnPagar.setOnClickListener {
            databaseAux.borrarDatosCarrito()
            Toast.makeText(this, "Se realizo el pago de los productos", Toast.LENGTH_SHORT).show()
        }

        // Listener para el boton catalogo
        btnBackC.setOnClickListener {
            finish()
        }


    }
}