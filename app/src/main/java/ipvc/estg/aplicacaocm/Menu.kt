package ipvc.estg.aplicacaocm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Menu : AppCompatActivity() {

    private lateinit var sPreferences: SharedPreferences
    private lateinit var logout: Button
    private lateinit var notas: Button
    private lateinit var mapa: Button
    private lateinit var addOcorrencia: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //logout, chamar sharedpref do main activity
        sPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        // logout btn
        logout = findViewById(R.id.logout)
        logout.setOnClickListener{
            val editor: SharedPreferences.Editor = sPreferences.edit()
            //clear e apply do novo edidtor
            editor.clear()
            editor.apply()

            val intent = Intent(this@Menu, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // notas btn
        notas = findViewById(R.id.notas)
        notas.setOnClickListener{
            val intent = Intent(this@Menu, ListaNota::class.java)
            startActivity(intent)
        }

        // mapa btn
        mapa = findViewById(R.id.mapa)
        mapa.setOnClickListener{
            val intent = Intent(this@Menu, MapsActivity::class.java)
            startActivity(intent)
        }

        //add ocorrencia btn
        addOcorrencia = findViewById(R.id.reportar)
        addOcorrencia.setOnClickListener{
            val intent = Intent(this@Menu, AddOcorrencia::class.java)
            startActivity(intent)
        }

    }
}