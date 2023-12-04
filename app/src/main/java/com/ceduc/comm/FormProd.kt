package com.ceduc.comm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormProd : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_prod)

        // Iniciar db pero hace referencia a la misma, por eso la llamo databaseAux
        val databaseAux = SQLiteDB(this)

        // Variables para recuperar los views
        var code = findViewById<EditText>(R.id.etxtCode)
        var description = findViewById<EditText>(R.id.etxtDescription)
        var price = findViewById<EditText>(R.id.etxtPrice)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnAddToCar = findViewById<Button>(R.id.btnAddToCar)
        val btnBackF = findViewById<Button>(R.id.btnBackF)

        // Recuperar los datos de la tabla porducto usando la funcion para mostrar los datos de esta tabla
        val listaProductos = databaseAux.mostrarDatosProductos()

        // Recuperar el dato del putExtra
        val datoIntent = intent.getStringExtra("clave")

        // Iniciar las variables auxiliares vacias
        var codeAux: String = ""
        var descriptionAux: String = ""
        var priceAux: String = ""

        /* Condicional que dependiendo del valor recibido desde del putExtra asignara los datos de cada producto
        respectivamente. Para esto se selecciona el indice que esta asignado en la lista.
        Se usa toString() porque el setText espera un string */
        if (datoIntent == "Dron") {
            codeAux = listaProductos.get(0).toString()
            descriptionAux = listaProductos.get(1).toString()
            priceAux = listaProductos.get(2).toString()
        } else {
            if (datoIntent == "MacPC") {
                codeAux = listaProductos.get(3).toString()
                descriptionAux = listaProductos.get(4).toString()
                priceAux = listaProductos.get(5).toString()
            } else {
                if (datoIntent == "Earphone") {
                    codeAux = listaProductos.get(6).toString()
                    descriptionAux = listaProductos.get(7).toString()
                    priceAux = listaProductos.get(8).toString()
                } else {
                    if (datoIntent == "VR") {
                        codeAux = listaProductos.get(9).toString()
                        descriptionAux = listaProductos.get(10).toString()
                        priceAux = listaProductos.get(11).toString()
                    }
                }
            }
        }

        // Mostrar los datos en el editText
        code.setText(codeAux)
        description.setText(descriptionAux)
        price.setText(priceAux)

        // Listener para el boton agregar al carrito que permite agregar datos al carrito y volver al main
        btnAddToCar.setOnClickListener {

            // Recuperar el dato que se ingreso el el EditText
            val codeAuxDB = codeAux.toString().toInt() //code.text.toString().toInt()
            val descriptionAuxDB = descriptionAux.toString() //description.text.toString()
            val priceAuxDB = priceAux.toString().toInt() //price.text.toString().toInt()

            //Agregar a la tabla carrito
            databaseAux.agregarDatosCarrito(codeAuxDB, descriptionAuxDB, priceAuxDB)
            Toast.makeText(this, "Se agrego el producto al carrito", Toast.LENGTH_SHORT).show()
        }

        // Listener para el boton actualizar que permite editar los datos de la tabla producto
        btnUpdate.setOnClickListener {

            // Variables que indican el codigo del producto y los datos que se van a actualizar. Estos datos se recuperan del EditText
            val codeProd = code.text.toString().toInt()
            val descriptionNew = description.text.toString()
            val priceNew = price.text.toString().toInt()

            // Llamar a la funcion para actualizar los datos del producto
            databaseAux.actualizarDatosProducto(codeProd, descriptionNew, priceNew)

            //Toast que indica la actualizacion de los datos
            Toast.makeText(this, "Se actualizaron correctamente los datos del producto", Toast.LENGTH_SHORT).show()
        }

        // Listener para el boton catalogo que permite volver al main
        btnBackF.setOnClickListener {
            finish()
        }
    }
}