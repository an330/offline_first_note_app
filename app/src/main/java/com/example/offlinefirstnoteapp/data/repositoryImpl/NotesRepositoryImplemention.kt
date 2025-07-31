package com.example.offlinefirstnoteapp.data.repositoryImpl

import android.util.Log
import com.example.offlinefirstnoteapp.data.local.NoteDao
import com.example.offlinefirstnoteapp.data.remote.FakeApi
import com.example.offlinefirstnoteapp.data.toEntity
import com.example.offlinefirstnoteapp.domain.Note
import com.example.offlinefirstnoteapp.domain.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.offlinefirstnoteapp.data.toNote


class NotesRepositoryImplemention(
    private val notesDao: NoteDao,
    private val fakeApi: FakeApi
):NotesRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map{ list->
            list.map{it.toNote()}


        }
    }
    override suspend fun addNotes(notes: Note) {
        Log.d("repoImplementition","repo is implementing")
        notesDao.insertNote(notes.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note.toEntity())
    }

    override suspend fun syncedNoteToServer() {
        val unSyncedNotes = notesDao.getUnSyncedNotes()
        for(noteEntity in unSyncedNotes){
            val note = noteEntity.toNote()
            if(fakeApi.uploadNote(note)){
                notesDao.insertNote(note.copy(isSynced = true).toEntity())
            }

        }
    }
}