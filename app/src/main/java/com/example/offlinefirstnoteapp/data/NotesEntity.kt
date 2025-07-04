package com.example.offlinefirstnoteapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="Notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    val title:String,
    val content:String,
    val isSynced:Boolean,
    val timeStamps:Long

)
