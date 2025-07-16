package com.example.offlinefirstnoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.offlinefirstnoteapp.data.local.NoteDao
import com.example.offlinefirstnoteapp.data.local.NotesEntity

@Database(entities =[NotesEntity::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun noteDao():NoteDao
}