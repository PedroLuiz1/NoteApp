package br.senac.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    //@ColumnInfo(name = "DESCRIPTION") - Mudar o nome da coluna no banco
    var desc: String
)


