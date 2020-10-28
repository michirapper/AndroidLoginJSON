package app.com.androidloginjson

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    var id = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val objetoIntent: Intent = intent
        id = objetoIntent.getStringExtra("id").toString()
        Toast.makeText(this, id, Toast.LENGTH_LONG).show()

        var btnRegistro = findViewById<Button>(R.id.btnRegistro)
        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var btnInformacion = findViewById<Button>(R.id.btnInformacion)
        btnInformacion.setEnabled(false)
        btnLogin.setEnabled(false)

        var nombreFichero = "ficheroInterno.json"

         var textoNoExiste = "{\"usuarios\": []}"

        //Comprobar si el JSON existe

        var file = File(getFilesDir().getAbsolutePath(), nombreFichero)
        if (!file.exists()) {
            var fileOutput = openFileOutput(nombreFichero, Context.MODE_PRIVATE)
            fileOutput.write(textoNoExiste.toByteArray())
            fileOutput.close()
        }




    }

    override fun onStart() {
        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var nombreFichero = "ficheroInterno.json"
        //Leer todo el texto

        var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombreFichero)))
        var textoLeido = bufferedReader.readLine()
        val todo = StringBuilder()
        while (textoLeido != null) {
            todo.append(textoLeido + "\n")
            textoLeido = bufferedReader.readLine()
        }
        bufferedReader.close()
        textoLeido = todo.toString()

        //Comprobar si hay algo en el fichero y deshabilita botone de Login

        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("usuarios")
        if (!jsonArray.toString().equals("[]")) {
            btnLogin.setEnabled(true)
        }
        var btnInformacion = findViewById<Button>(R.id.btnInformacion)
        if (id != "null") {
            btnInformacion.setEnabled(true)
        }
        super.onStart()
    }

    fun goRegistro(view: View) {
        var miIntent = Intent(this, Registro::class.java)
        startActivity(miIntent)

    }

    fun goLogin(view: View) {
        var miIntent = Intent(this, Login::class.java)
        startActivity(miIntent)

    }

    fun goInformacion(view: View) {
        val objetoIntent: Intent = intent
        var miIntent = Intent(this, Informacion::class.java)
        id = objetoIntent.getStringExtra("id").toString()
        miIntent.putExtra("id", id)
        startActivity(miIntent)
    }

}