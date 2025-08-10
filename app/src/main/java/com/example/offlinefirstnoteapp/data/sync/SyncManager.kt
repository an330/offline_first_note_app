package com.example.offlinefirstnoteapp.data.sync

import android.util.Log
import com.example.offlinefirstnoteapp.data.local.NoteDao
import com.example.offlinefirstnoteapp.data.remote.FirestoreNoteService
import com.example.offlinefirstnoteapp.data.toEntity
import com.example.offlinefirstnoteapp.data.toNote
import com.example.offlinefirstnoteapp.domain.NotesRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class SyncManager @Inject constructor(
    private val noteDao: NoteDao,
    private val fireNoteService: FirestoreNoteService,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun syncToFireStore() = withContext(ioDispatcher) {
        Log.d("SyncManager", "Starting sync to Firestore...")

        val unSyncedNotes = noteDao.getUnSyncedNotes()
        Log.d("SyncManager", "Found ${unSyncedNotes.size} unsynced notes")

        if (unSyncedNotes.isEmpty()) {
            Log.d("SyncManager", "No notes to sync, exiting.")
            return@withContext
        }

       unSyncedNotes.forEachIndexed{index,noteEntity ->
           val note = noteEntity.toNote()
           Log.d("SyncManager","[${index+1}/${unSyncedNotes.size}] Attempting to push note:${note.id},title:${note.title}")

           try {
               val success = withTimeout(5000){
                   fireNoteService.pushNote(note)
               }
               if(success){
                  Log.d("SyncManager","Note ${note.id} synced updating local db...")
                   noteDao.updateNote(note.copy(isSynced = true).toEntity())
               }
               else {
                   Log.w("SyncManager", "⚠️ Note ${note.id} failed to sync (service returned false).")
               }
           }catch(e:FirebaseFirestoreException) {
               Log.e(
                   "SyncManager",
                   "❌ Firestore error on note ${note.id} | Code: ${e.code} | Message: ${e.message}"
               )
               if(e.code == FirebaseFirestoreException.Code.PERMISSION_DENIED){
                   Log.w("SyncManager", "🚫 Skipping note ${note.id} due to permission error.")
               }
           } catch (e: TimeoutCancellationException) {
               Log.e("SyncManager", "⏳ Timeout pushing note ${note.id} after 5000ms", e)

           } catch (e: Exception) {
               Log.e("SyncManager", "💥 Unexpected exception syncing note ${note.id}", e)
           }

           delay(300) // avoid hammering Firestore

           }

    }

}
