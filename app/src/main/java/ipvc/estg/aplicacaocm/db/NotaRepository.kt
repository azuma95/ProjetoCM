package ipvc.estg.aplicacaocm.db

import androidx.lifecycle.LiveData
import ipvc.estg.aplicacaocm.dao.NotaDao
import ipvc.estg.aplicacaocm.entities.Nota

class NotaRepository(private val notaDao: NotaDao) {

    val allNotas: LiveData<List<Nota>> = notaDao.getAlphabetizedNotes()

    suspend fun insert(nota: Nota) {
        notaDao.insert(nota)
    }

    fun deleteById(id: Int) {
        notaDao.deleteById(id)
    }
    suspend fun updateById(titulo: String, descricao: String, id: Int){
        notaDao.updateById(titulo, descricao, id)
    }
}