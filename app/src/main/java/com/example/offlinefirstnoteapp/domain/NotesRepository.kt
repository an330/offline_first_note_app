package com.example.offlinefirstnoteapp.domain

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getAllNotes() : Flow<List<Note>>
    suspend fun addNotes(notes:Note)
    suspend fun deleteNote(note:Note)
    suspend fun syncedNoteToServer()

}