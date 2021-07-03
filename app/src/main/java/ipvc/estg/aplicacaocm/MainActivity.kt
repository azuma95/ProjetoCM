package ipvc.estg.aplicacaocm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.aplicacaocm.adapters.NotaListAdapter
import ipvc.estg.aplicacaocm.entities.Nota
import ipvc.estg.aplicacaocm.viewModel.NotaViewModel

var PARAM_ID = "id"
var PARAM1_TITLE = "titulo"
var PARAM2_CONTENT = "descricao"

class MainActivity : AppCompatActivity(), NotaEspecificaListener {

    private lateinit var notaViewModel: NotaViewModel
    private val newWordActivityRequestCode = 1
    private val UpdateNotaActivityRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotaListAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //view model
        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaViewModel.allNotas.observe(this, { notas ->
            notas?.let { adapter.setNotas(it) }
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNota::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null && data.action == "REMOVE") {
            var id = Integer.parseInt(data?.getStringExtra(PARAM_ID))
            notaViewModel.deleteById(id)
            Toast.makeText(this, R.string.notaEliminada, Toast.LENGTH_SHORT).show()
            return
        }


        if(requestCode ==  newWordActivityRequestCode) {
            if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
                var titulo = data?.getStringExtra(AddNota.EXTRA_REPLY_TITLE).toString()
                var descricao = data?.getStringExtra(AddNota.EXTRA_REPLY_CONTENT).toString()
                var nota = Nota(titulo = titulo, descricao = descricao)
                notaViewModel.insert(nota)
                Toast.makeText(this, R.string.notaGuardada, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.notaNInserida, Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == UpdateNotaActivityRequestCode) {
            if (requestCode == UpdateNotaActivityRequestCode && resultCode == Activity.RESULT_OK) {
                var titulo = data?.getStringExtra(PARAM1_TITLE).toString()
                var descricao = data?.getStringExtra(PARAM2_CONTENT).toString()
                var id = Integer.parseInt(data?.getStringExtra(PARAM_ID))
                notaViewModel.updateById(titulo, descricao, id)
                Toast.makeText(applicationContext, R.string.notaAlterada, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.notaNInserida, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNotaEspecificaListener(data: Nota) {
        val id = data.id.toString()
        val titulo = data.titulo
        val descricao = data.descricao
        val intent = Intent(this, EditaNota::class.java).apply {
            putExtra(PARAM_ID, id)
            putExtra(PARAM1_TITLE, titulo)
            putExtra(PARAM2_CONTENT, descricao)
        }
        startActivityForResult(intent, UpdateNotaActivityRequestCode)
    }
}