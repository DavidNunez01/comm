package com.ceduc.comm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Listar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        // Iniciar db pero hace referencia a la misma, por eso la llamo databaseAux
        val databaseAux = SQLiteDB(this)

        //Recuperar los views

        val txtPrductos = findViewById<TextView>(R.id.txtListaProd)
        val btnBackL = findViewById<Button>(R.id.btnBackL)

        // Mostrar los datos de la tabla producto
        val listaDatos = databaseAux.mostrarDatosProductos()

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
            txtPrductos.append("Producto $numProd\nCódigo: $code\nDescripción: $description\nPrecio: $price\n\n\n")
        }


        btnBackL.setOnClickListener {
            finish()
        }

    }
}