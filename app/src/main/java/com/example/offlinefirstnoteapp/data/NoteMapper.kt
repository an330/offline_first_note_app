package com.example.offlinefirstnoteapp.data

import android.util.Log
import com.example.offlinefirstnoteapp.data.local.NotesEntity
import com.example.offlinefirstnoteapp.domain.Note

fun NotesEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        isSynced = isSynced,
        timestamps = timeStamps
    )
}

fun Note.toEntity(): NotesEntity {
    Log.d("NoteToEntity", "Converting Note to NotesEntity: id=$id, title=$title, content=$content, isSynced=$isSynced, timestamps=$timestamps")
    return NotesEntity(
        id = id,
        title = title,
        content = content,
        isSynced = isSynced,
        timeStamps = timestamps
    )
}