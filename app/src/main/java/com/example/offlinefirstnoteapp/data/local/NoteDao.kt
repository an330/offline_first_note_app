package com.example.offlinefirstnoteapp.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface NoteDao{
    @Query("SELECT * FROM Notes")
    fun getAllNotes(): Flow<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertNote(note: NotesEntity)

    @Delete
    suspend fun deleteNote(note: NotesEntity)

    @Query("SELECT * FROM Notes WHERE isSynced =0")
    suspend fun getUnSyncedNotes():List<NotesEntity>
}