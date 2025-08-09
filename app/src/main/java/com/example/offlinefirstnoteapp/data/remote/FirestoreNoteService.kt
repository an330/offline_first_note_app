package com.example.offlinefirstnoteapp.data.remote

import com.example.offlinefirstnoteapp.domain.Note
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

object FirestoreNoteService {

    private val fireStore = FirebaseFirestore.getInstance()
    suspend fun pushNote(note: Note):Boolean{
        return try {
            fireStore.collection("notes")
                .document(note.id.toString())
                .set(note)
                .await()
            true
        }catch (e:Exception){
            e.printStackTrace()
            false
        }
    }
}