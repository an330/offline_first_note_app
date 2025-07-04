package com.example.offlinefirstnoteapp.domain


data class Note(
    val id: Int =0,
    val title : String,
    val content :String,
    val isSynced :Boolean,
    val timestamps:Long = System.currentTimeMillis()
    )