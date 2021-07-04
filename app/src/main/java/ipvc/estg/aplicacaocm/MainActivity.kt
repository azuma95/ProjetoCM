package ipvc.estg.aplicacaocm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notasButton = findViewById<Button>(R.id.buttonNotas)
        notasButton.setOnClickListener{
            val intent = Intent(this@MainActivity, ListaNota::class.java)
            startActivity(intent)
        }
    }
}