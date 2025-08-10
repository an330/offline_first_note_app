package com.example.offlinefirstnoteapp.data.repositoryImpl

import android.util.Log
import com.example.offlinefirstnoteapp.data.local.NoteDao
import com.example.offlinefirstnoteapp.data.remote.FakeApi
import com.example.offlinefirstnoteapp.data.remote.FirestoreNoteService
import com.example.offlinefirstnoteapp.data.sync.SyncManager
import com.example.offlinefirstnoteapp.data.toEntity
import com.example.offlinefirstnoteapp.domain.Note
import com.example.offlinefirstnoteapp.domain.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.offlinefirstnoteapp.data.toNote


class NotesRepositoryImplemention(
    private val notesDao: NoteDao,
    private val fakeApi: FakeApi,
    private val syncManager: SyncManager
):NotesRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map{ list->
            list.map{it.toNote()}
           // list.sortedByDescending { it.id }.map { it.toNote() }


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
        syncManager.syncToFireStore()
    }

    override suspend fun reStoreNotes(copy: Note) {
        notesDao.insertNote(copy.toEntity())
    }
}