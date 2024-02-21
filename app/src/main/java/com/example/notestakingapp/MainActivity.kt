package com.example.notestakingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notestakingapp.db.Note
import com.example.notestakingapp.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() , NoteAdapter.ClickListener {
    private lateinit var noteAdapter :NoteAdapter
    private lateinit  var noteViewModel: NoteViewModel
    private lateinit var noteViewModelFactory: NoteViewModelFactory
    private lateinit var repo: Repo
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var rv : RecyclerView
    private lateinit var dialog: Dialog
    private lateinit var dialogName : EditText
    private lateinit var dialogContent :EditText
    private lateinit var dialogSaveNoteBtn :Button


    private lateinit var fab:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()


        noteViewModel.getAllNotes().observe(this){
            noteAdapter = NoteAdapter(it,this)
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = noteAdapter

        }


        fab.setOnClickListener {
            openDialog(true)
        }
    }

    private fun openDialog(comFromFab :Boolean) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.note_dialog)

        dialogName = dialog.findViewById(R.id.dialog_name)
        dialogContent = dialog.findViewById(R.id.dialog_content)

        dialogSaveNoteBtn = dialog.findViewById(R.id.dialog_save_note_btn)


        dialogSaveNoteBtn.setOnClickListener {
            val note = Note(
                noteName= dialogName.text.toString(),
                noteContent = dialogContent.text.toString()
            )
            if (!comFromFab){
                noteViewModel.update(note)
            }else{
               noteViewModel.insert(note)
            }

            dialog.dismiss()

        }

        dialog.show()

    }


    private fun init(){
        noteDatabase = NoteDatabase(this)
        repo = Repo(noteDatabase.getNoteDao())
        noteViewModelFactory = NoteViewModelFactory(repo)
        noteViewModel = ViewModelProvider(this,noteViewModelFactory)[NoteViewModel::class.java]
        rv = findViewById(R.id.rv)
        fab  = findViewById(R.id.floatingActionButton)
    }

    override fun update(note: Note) {
        openDialog(false)
    }

    override fun delete(note: Note) {
        noteViewModel.delete(note)
    }
}





























