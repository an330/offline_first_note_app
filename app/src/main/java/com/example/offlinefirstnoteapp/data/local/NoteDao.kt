package com.example.offlinefirstnoteapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao{
    @Query("SELECT * FROM Notes ORDER BY timeStamps  DESC")
    fun getAllNotes(): Flow<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertNote(note: NotesEntity)

    @Delete
    suspend fun deleteNote(note: NotesEntity)

    @Query("SELECT * FROM Notes WHERE isSynced =0")
    suspend fun getUnSyncedNotes():List<NotesEntity>

    @Update
    suspend fun updateNote(note:NotesEntity)
}