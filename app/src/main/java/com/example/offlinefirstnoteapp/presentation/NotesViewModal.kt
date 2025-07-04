package com.example.offlinefirstnoteapp.presentation

import androidx.lifecycle.ViewModel
import com.example.offlinefirstnoteapp.domain.NotesRepository
import com.example.offlinefirstnoteapp.usecase.GetNoteUseCase

class NotesViewModal(
    private val getNotes: GetNoteUseCase,
    private val repository: NotesRepository
): ViewModel() {

}