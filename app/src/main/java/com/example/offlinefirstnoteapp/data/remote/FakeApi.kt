package com.example.offlinefirstnoteapp.data.remote
import com.example.offlinefirstnoteapp.domain.Note
interface  FakeApi {
    suspend fun uploadNote(note: Note): Boolean
}