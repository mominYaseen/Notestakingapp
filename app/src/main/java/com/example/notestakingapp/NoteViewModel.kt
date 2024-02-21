package com.example.notestakingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notestakingapp.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repo: Repo
) :ViewModel(){

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(note)
        }
    }
    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(note)
        }
    }
    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(note)
        }
    }
    fun getAllNotes() = repo.getAllNote()
}