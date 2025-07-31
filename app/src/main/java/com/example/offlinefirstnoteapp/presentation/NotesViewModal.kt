package com.example.offlinefirstnoteapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinefirstnoteapp.domain.NotesRepository
import com.example.offlinefirstnoteapp.usecase.GetNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import com.example.offlinefirstnoteapp.domain.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModal@Inject constructor(
    private val getNotes: GetNoteUseCase,
    private val repository: NotesRepository
): ViewModel() {

    val notes = getNotes().stateIn(viewModelScope, SharingStarted.Lazily,emptyList())

    fun addNotes(note:Note) {
        viewModelScope.launch{
            repository.addNotes(note)
            syncNotes()
        }
    }

    fun deleteNote(note:Note) = {viewModelScope.launch{repository.deleteNote(note)}}

    fun syncNotes() = {viewModelScope.launch{repository.syncedNoteToServer()}}
}