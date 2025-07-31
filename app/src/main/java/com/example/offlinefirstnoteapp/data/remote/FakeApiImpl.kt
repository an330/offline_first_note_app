package com.example.offlinefirstnoteapp.data.remote

import com.example.offlinefirstnoteapp.domain.Note
import kotlinx.coroutines.delay

class FakeApiImpl:FakeApi {
    override suspend fun uploadNote(note: Note): Boolean {
        delay(1000)
        println("Uploading note to fake server: ${note.title}")
        return true
    }
}