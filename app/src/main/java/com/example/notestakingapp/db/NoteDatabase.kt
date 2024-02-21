package com.example.notestakingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun getNoteDao():NoteDao

    companion object{
        private const val DB_NAME = "note_database.db"
        private  var INSTANCE : NoteDatabase ?=null


        operator fun invoke(context: Context)= INSTANCE?: synchronized(Any()){
            INSTANCE?:buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            DB_NAME
        ).build()

    }

}