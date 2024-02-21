package com.example.notestakingapp

import com.example.notestakingapp.db.Note
import com.example.notestakingapp.db.NoteDao

class Repo ( private val dao: NoteDao){

    fun insert(note:Note){
        dao.insert(note)
    }

    fun update(note:Note){
        dao.update(note)
    }

    fun delete(note:Note){
        dao.delete(note)
    }

    fun getAllNote() = dao.getAllNotes()
}