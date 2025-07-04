package com.example.offlinefirstnoteapp.domain

import kotlinx.coroutines.flow.Flow


class NoteUseCase(private val repository:NotesRepository) {
    operator fun invoke() : Flow<List<Note>> = repository.getAllNotes()
}