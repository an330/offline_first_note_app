package com.example.offlinefirstnoteapp.data.sync

import com.example.offlinefirstnoteapp.data.local.NoteDao
import com.example.offlinefirstnoteapp.data.remote.FirestoreNoteService
import com.example.offlinefirstnoteapp.data.toEntity
import com.example.offlinefirstnoteapp.data.toNote
import com.example.offlinefirstnoteapp.domain.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncManager @Inject constructor (
    private val noteDao: NoteDao,
    private val fireNoteService:FirestoreNoteService,
    private val  ioDispatcher: CoroutineDispatcher
){
    suspend fun SyncToFireStore() = withContext(ioDispatcher){
        val unSyncedNotes = noteDao.getUnSyncedNotes()
        unSyncedNotes.forEach { noteEntity->
            val notes = noteEntity.toNote()
            try{
                val success = fireNoteService.pushNote(notes)
                if(success){
                    noteDao.updateNote(notes.copy(isSynced = true).toEntity())
                }
            }catch(e:Exception){
                e.printStackTrace()
            }
        }
    }
}