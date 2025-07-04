package com.example.offlinefirstnoteapp.data

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

fun Note.toEntity():NotesEntity {
    return NotesEntity(
        id = id,
        title = title,
        content = content,
        isSynced = isSynced,
        timeStamps = timestamps
    )
}