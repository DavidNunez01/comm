package com.ceduc.comm


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteDB (context: Context):SQLiteOpenHelper (context,"producto", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE producto (codigo INTEGER PRIMARY KEY, descripcion TEXT, precio INTEGER);"
        db?.execSQL(query)

        insertarDatosIniciales(db) // Llamar a la funcion insertarDatosInciales() creada mas abajo

        val query2 = "CREATE TABLE carrito (codigo INTEGER PRIMARY KEY, descripcion TEXT, precio INTEGER);"
        db?.execSQL(query2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var dropQuery = "DROP TABLE IF EXISTS producto;"
        db?.execSQL(dropQuery)
    }

    // Funcion que permite ingresar datos de los productos al crear la DB
    fun insertarDatosIniciales(db: SQLiteDatabase?) {

        val contenedor = ContentValues()

        // Insertar múltiples registros
        contenedor.put("codigo", 101)
        contenedor.put("descripcion", "Dron")
        contenedor.put("precio", 1000)
        db?.insert("producto", null, contenedor)

        contenedor.clear() // Limpiar el contenedor

        contenedor.put("codigo", 102)
        contenedor.put("descripcion", "MacPC")
        contenedor.put("precio", 1500)
        db?.insert("producto", null, contenedor)

        contenedor.clear()

        contenedor.put("codigo", 103)
        contenedor.put("descripcion", "Earphone")
        contenedor.put("precio", 700)
        db?.insert("producto", null, contenedor)

        contenedor.clear()

        contenedor.put("codigo", 104)
        contenedor.put("descripcion", "VR")
        contenedor.put("precio", 2000)
        db?.insert("producto", null, contenedor)

    }

    // Funcion agrega datos a la tabla carrito
    fun agregarDatosCarrito(codigo: Int, descripcion: String, precio: Int){
        val dbEditable = this.writableDatabase //editar db
        val contenedor = ContentValues() //contendor
        contenedor.put("codigo", codigo)
        contenedor.put("descripcion", descripcion)
        contenedor.put("precio", precio)
        dbEditable.insert("carrito", null, contenedor)
        dbEditable.close()
    }

    // Función para mostrar los datos de la tabla carrito
    fun mostrarDatosCarrito(): MutableList<Any> {
        val lista = mutableListOf<Any>() // Iniciara lista que contendrá los datos

        val dbEditable = this.readableDatabase
        val cursor = dbEditable.rawQuery("SELECT * FROM carrito", null)
        // Recorriendo el cursor para obtener y mostrar los datos
            while (cursor.moveToNext()) {
                val codigo = cursor.getInt(0)
                val descripcion = cursor.getString(1)
                val precio = cursor.getInt(2)

                // Agregar los datos a la lista
                lista.add(codigo)
                lista.add(descripcion)
                lista.add(precio)
            }
        cursor.close()
        dbEditable.close()

        return lista // Devolver la lista con los datos almacenados
    }

    // Función para mostrar los datos de la tabla producto
    fun mostrarDatosProductos(): MutableList<Any> {
        val lista = mutableListOf<Any>() // Iniciara lista que contendrá los datos

        val dbEditable = this.readableDatabase
        val cursor = dbEditable.rawQuery("SELECT * FROM producto", null)
        // Recorriendo el cursor para obtener y mostrar los datos
        while (cursor.moveToNext()) {
            val codigo = cursor.getInt(0)
            val descripcion = cursor.getString(1)
            val precio = cursor.getInt(2)

            // Agregar los datos a la lista
            lista.add(codigo)
            lista.add(descripcion)
            lista.add(precio)
        }
        cursor.close()
        dbEditable.close()

        return lista // Devolver la lista con los datos almacenados
    }

    //Funcion que permite borrar todos los datos de la tabla carrito
    fun borrarDatosCarrito(){

        val dbEditable = this.writableDatabase
        dbEditable.delete("carrito", null, null)
        dbEditable.close()
    }

    //Funcion que permite borrar todos los datos de la tabla producto
    fun borrarDatosProducto(){

        val dbEditable = this.writableDatabase
        dbEditable.delete("producto", null, null)
        dbEditable.close()
    }

    // Funcion que permite actualizar los datos de la tabla producto
    fun actualizarDatosProducto(codigo: Int, nuevaDescripcion: String, nuevoPrecio: Int): Int {
        val dbEditable = this.writableDatabase

        //Contenedor que almacena los datos a actualizar
        val contenedor = ContentValues()
        contenedor.put("descripcion", nuevaDescripcion)
        contenedor.put("precio", nuevoPrecio)

        // Realizar la actualización y obtener el número de filas afectadas
        val filasActualizadas = dbEditable.update("producto", contenedor, "codigo = ?", arrayOf(codigo.toString()))
        // 'codigo = ?' es una clausura where
        // 'arrayOf(codigo.toString()))' convierte el codigo a string y lo almacena en un array para remplazarlo en 'codigo = ?'
        // Esto es porque el update espera recibir un array con elementos en tipo string

        dbEditable.close()

        return filasActualizadas
    }

    // Funcion que permite actualizar los datos de la tabla carrito
    fun actualizarDatosCarrito(codigo: Int, nuevaDescripcion: String, nuevoPrecio: Int): Int {
        val dbEditable = this.writableDatabase

        //Contenedor que almacena los datos a actualizar
        val contenedor = ContentValues()
        contenedor.put("descripcion", nuevaDescripcion)
        contenedor.put("precio", nuevoPrecio)

        // Realizar la actualización y obtener el número de filas afectadas
        val filasActualizadas = dbEditable.update("carrito", contenedor, "codigo = ?", arrayOf(codigo.toString()))
        // 'codigo = ?' es clausura where
        // 'arrayOf(codigo.toString()))' convierte el codigo a string y lo almacena en un array para remplazarlo en 'codigo = ?'
        // Esto es porque el update espera recibir un array con elementos en tipo string

        dbEditable.close()

        return filasActualizadas
    }
}