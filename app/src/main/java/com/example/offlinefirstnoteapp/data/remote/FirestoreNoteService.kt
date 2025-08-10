package com.example.offlinefirstnoteapp.data.remote

import android.util.Log
import com.example.offlinefirstnoteapp.domain.Note
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout

object FirestoreNoteService {

    private val fireStore = FirebaseFirestore.getInstance()

    suspend fun pushNote(note: Note): Boolean {
        return try {
            Log.d("FirestoreNoteService", "Attempting to push note: ${note.id}, title: ${note.title}")

            withTimeout(5000) { // 5 seconds timeout
                fireStore.collection("notes")
                    .document(note.id.toString())
                    .set(note)
                    .await()
            }

            Log.d("FirestoreNoteService", "Successfully pushed note: ${note.id}")
            true
        }catch (e:FirebaseFirestoreException){
            Log.e("FireStoreNoteService","Firebase error while pushing note:${note.id} | Code :${e.code} | Message :${e.message}",e)
            false
        }
        catch (e: Exception) {
            Log.e(
                "FirestoreNoteService",
                "Failed to push note: ${note.id}, type: ${e::class.java.simpleName}, message: ${e.message}",
                e
            )
            false
        }
    }
}