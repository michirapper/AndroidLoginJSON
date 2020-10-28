package app.com.androidloginjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Informacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)
        val objetoIntent: Intent = intent
        var id = objetoIntent.getStringExtra("id").toString()
        Toast.makeText(this, id, Toast.LENGTH_LONG).show()
    }
}