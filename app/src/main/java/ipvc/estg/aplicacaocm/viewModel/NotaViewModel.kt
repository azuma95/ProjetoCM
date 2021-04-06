package ipvc.estg.aplicacaocm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.aplicacaocm.db.NotaDB
import ipvc.estg.aplicacaocm.db.NotaRepository
import ipvc.estg.aplicacaocm.entities.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotaViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: NotaRepository

    val allNotas: LiveData<List<Nota>>
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    init {
        val notasDao = NotaDB.getDatabase(application, viewModelScope).notaDao()
        repository = NotaRepository(notasDao)
        allNotas = repository.allNotas
    }

    fun insert(nota: Nota) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(nota)
    }

    fun deleteById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteById(id)
    }

    fun updateById(titulo: String, descricao: String, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateById(titulo, descricao, id)
    }
}