package app.com.androidloginjson

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class Registro : AppCompatActivity() {
    var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
    }

    fun Cancelar(view: View) {
        onBackPressed()
    }

    fun Anadir(view: View) {
        var nombre = findViewById<EditText>(R.id.editTextTextNombre)
        var apellidos = findViewById<EditText>(R.id.editTextTextApellidos)
        var user = findViewById<EditText>(R.id.editTextTextUsuario)
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
        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("usuarios")
        for (i in 0 until jsonArray.length()) {
            num = i+1
        }
        val main = JSONObject()
        main.put("id", num)
        main.put("nombre", nombre.text)
        main.put("Apellido", apellidos.text)
        main.put("user", user.text)
        main.put("contrasena", pass.text)
        jsonArray.put(main)
        var fileOutput = openFileOutput(nombreFichero, Context.MODE_PRIVATE)
        fileOutput.write(jsonObject.toString().toByteArray())
        fileOutput.close()
    }
}