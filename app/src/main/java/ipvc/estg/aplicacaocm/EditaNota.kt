package ipvc.estg.aplicacaocm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class EditaNota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_nota)

        var editaTitulo: EditText = findViewById(R.id.update_title)
        var editaDescricao: EditText = findViewById(R.id.update_content)

        var id = intent.getStringExtra(PARAM_ID)
        var titulo = intent.getStringExtra(PARAM1_TITLE)
        var descricao = intent.getStringExtra(PARAM2_CONTENT)

        editaTitulo.setText(titulo.toString())
        editaDescricao.setText(descricao.toString())

        val button_remove = findViewById<Button>(R.id.button_remove)
        button_remove.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(PARAM_ID, id.toString())
            replyIntent.setAction("REMOVE")
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }

        val button_update = findViewById<Button>(R.id.button_update)
        button_update.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(PARAM_ID, id)
            if (TextUtils.isEmpty(editaTitulo.text) || TextUtils.isEmpty(editaDescricao.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val titulo = editaTitulo.text.toString()
                replyIntent.putExtra(PARAM1_TITLE, titulo)

                val descricao = editaDescricao.text.toString()
                replyIntent.putExtra(PARAM2_CONTENT, descricao)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}