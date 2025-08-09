package com.example.offlinefirstnoteapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinefirstnoteapp.core.NetworkMonitor
import com.example.offlinefirstnoteapp.domain.NotesRepository
import com.example.offlinefirstnoteapp.usecase.GetNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import com.example.offlinefirstnoteapp.domain.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModal @Inject constructor(
    private val getNotes: GetNoteUseCase,
    private val repository: NotesRepository,
    private val networkMonitor:NetworkMonitor

): ViewModel() {

    val notes = getNotes().stateIn(viewModelScope, SharingStarted.Lazily,emptyList())
    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collect{isConnected ->
                if(isConnected){
                    syncNotes()
                }
            }
        }
    }
    fun refreshNotes(note: Note){
        viewModelScope.launch { repository.getAllNotes() }
    }

    fun addNotes(note:Note) {
        viewModelScope.launch{
            repository.addNotes(note)
           // syncNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }


    fun syncNotes() {
        viewModelScope.launch{repository.syncedNoteToServer()}
    }

    fun restoreNode(note: Note) {
          viewModelScope.launch { repository.reStoreNotes(note.copy(isSynced = false)) }
    }
}