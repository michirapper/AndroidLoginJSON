package app.com.androidloginjson

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        //Comprobar si hay algo en el fichero y deshabilitar botones
        
        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("usuarios")
        if (!jsonArray.toString().equals("[]")) {
            btnLogin.setEnabled(true)
        }

    }

    fun goRegistro(view: View) {
        var miIntent = Intent(this, Registro::class.java)
        startActivity(miIntent)
    }
}