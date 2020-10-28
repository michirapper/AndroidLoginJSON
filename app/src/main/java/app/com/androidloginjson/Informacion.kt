package app.com.androidloginjson

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class Informacion : AppCompatActivity() {
    var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)
    }

    override fun onStart() {
        val objetoIntent: Intent = intent
        var id = objetoIntent.getStringExtra("id")
         Toast.makeText(this, id, Toast.LENGTH_LONG).show()
        var nombre = findViewById<EditText>(R.id.editTextTextNombre)
        var apellidos = findViewById<EditText>(R.id.editTextTextApellidos)
        var users = findViewById<EditText>(R.id.editTextTextUsuario)
        var pass = findViewById<EditText>(R.id.editTextTextContrasena)

        var nombreFichero = "ficheroInterno.json"
        var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombreFichero)))
        var textoLeido = bufferedReader.readLine()
        val todo = StringBuilder()
        while (textoLeido != null) {
            todo.append(textoLeido + "\n")
            textoLeido = bufferedReader.readLine()
        }
        textoLeido = todo.toString()
        bufferedReader.close()

        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("usuarios")
        for (i in 0 until jsonArray.length()) {

            val jsonObject = jsonArray.getJSONObject(i)

            if (jsonObject.optString("id").equals(id)) {
                val id = jsonObject.optString("id").toInt()
                val name = jsonObject.optString("nombre")
                val apellido = jsonObject.optString("Apellido")
                val user = jsonObject.optString("user")
                val contrasena = jsonObject.optString("contrasena")

                nombre.setText(name)
                apellidos.setText(apellido)
                users.setText(user)
                pass.setText(contrasena)
            }
        }
        super.onStart()
    }

    fun Cancelar(view: View) {
        onBackPressed()
    }

    fun Actualizar(view: View) {
        val objetoIntent: Intent = intent
        var id = objetoIntent.getStringExtra("id")!!.toInt()
        var nombre = findViewById<EditText>(R.id.editTextTextNombre)
        var apellidos = findViewById<EditText>(R.id.editTextTextApellidos)
        var users = findViewById<EditText>(R.id.editTextTextUsuario)
        var pass = findViewById<EditText>(R.id.editTextTextContrasena)

        var nombreFichero = "ficheroInterno.json"
        var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombreFichero)))
        var textoLeido = bufferedReader.readLine()
        val todo = StringBuilder()
        while (textoLeido != null) {
            todo.append(textoLeido + "\n")
            textoLeido = bufferedReader.readLine()
        }
        textoLeido = todo.toString()
        bufferedReader.close()

        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("usuarios")

        jsonArray.remove(id)

        val main = JSONObject()
        main.put("id", num)
        main.put("nombre", nombre.text)
        main.put("Apellido", apellidos.text)
        main.put("user", users.text)
        main.put("contrasena", pass.text)
        jsonArray.put(main)
        var fileOutput = openFileOutput(nombreFichero, Context.MODE_PRIVATE)
        fileOutput.write(jsonObject.toString().toByteArray())
        fileOutput.close()


    }
}