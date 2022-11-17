package br.senac.noteapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.senac.noteapp.databinding.ActivityListNotesBinding
import br.senac.noteapp.databinding.NotaBinding
import br.senac.noteapp.db.Database
import br.senac.noteapp.model.Note

class ListNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityListNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val i = Intent(this, NewNoteActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()

        listarNotas()
    }

    fun listarNotas() {
        val db = Room.databaseBuilder(this, Database::class.java, "notes").build()

        //Rodar código do Run
        Thread {
            val notas = db.noteDao().listar()

            //Rodar código que atualiza a tela
            runOnUiThread {
                atualizarTela(notas)
            }
        }.start()
    }

    fun atualizarTela(notas: List<Note>) {
        //0 - Remover tudo do container
        binding.container.removeAllViews()

        //Para cada nota:
        notas.forEach {
            //1 - Inflar o layout da nota
            val notaBinding = NotaBinding.inflate(layoutInflater)

            //2 - Modificar o conteúdo do layout com os dados da nota
            notaBinding.textTitulo.text = it.title
            notaBinding.textDesc.text = it.desc

            //3 - Jogar a nota no container
            binding.container.addView(notaBinding.root)
        }

    }
}