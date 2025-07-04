package com.example.offlinefirstnoteapp.usecase

import com.example.offlinefirstnoteapp.domain.Note
import com.example.offlinefirstnoteapp.domain.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNoteUseCase(
    private val repository: NotesRepository
) {
    operator fun invoke(): Flow<List<Note>> = repository.getAllNotes()
}