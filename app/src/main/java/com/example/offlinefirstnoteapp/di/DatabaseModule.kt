package com.example.offlinefirstnoteapp.di

import android.content.Context
import androidx.room.Room
import com.example.offlinefirstnoteapp.data.NoteDatabase
import com.example.offlinefirstnoteapp.data.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):NoteDatabase{
        return Room.databaseBuilder(context,NoteDatabase::class.java,"notes-db").build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(db:NoteDatabase):NoteDao = db.noteDao()
}