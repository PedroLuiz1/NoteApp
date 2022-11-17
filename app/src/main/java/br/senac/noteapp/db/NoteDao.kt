package br.senac.noteapp.db

import androidx.room.*
import br.senac.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert
    fun inserir(note: Note)

    @Query("SELECT * FROM note")
    fun listar(): List<Note>

    /*
    @Delete
    fun excluir(note: Note)

    @Update
    fun atualizar(note: Note)

    @Query("SELECT * FROM Note WHERE title LIKE :titulo")
    fun pesquisar(titulo: String)
    */
}