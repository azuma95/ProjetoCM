package ipvc.estg.aplicacaocm.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.aplicacaocm.entities.Nota

@Dao
interface NotaDao {

    @Query("SELECT * from nota_table ORDER BY titulo ASC")
    fun getAlphabetizedNotes(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Update
    suspend fun updateNota(nota: Nota)

    @Query("DELETE FROM nota_table")
    suspend fun deleteAll()

    @Query("DELETE FROM nota_table WHERE id == :id")
    fun deleteById(id: Int)

    @Query("UPDATE nota_table SET titulo=:titulo, descricao=:descricao WHERE id == :id")
    suspend fun updateById(titulo: String, descricao: String, id: Int)
}