package com.example.notestakingapp

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notestakingapp.db.Note

class NoteAdapter(
    private val listOfNotes : List<Note>,
    private val clickListener: ClickListener
):RecyclerView.Adapter<NoteAdapter.NotesViewHolder>(){

    class NotesViewHolder(itemView : View) : ViewHolder(itemView){
        val textNoteName : TextView = itemView.findViewById(R.id.note_name)
        val textNoteContent:TextView = itemView.findViewById(R.id.note_content)
        val deleteNote : Button = itemView.findViewById(R.id.delete_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_layout,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currNote = listOfNotes[position]
        holder.textNoteName.text = currNote.noteName
        holder.textNoteContent.text = currNote.noteContent

        holder.itemView.setOnClickListener {
            clickListener.update(currNote)
        }

        holder.deleteNote.setOnClickListener {
            clickListener.delete(currNote)
        }

    }

    interface ClickListener{
        fun update(note: Note)
        fun delete(note: Note)
    }
}