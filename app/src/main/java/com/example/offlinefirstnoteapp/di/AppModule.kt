package com.example.offlinefirstnoteapp.di

import com.example.offlinefirstnoteapp.data.local.NoteDao
import com.example.offlinefirstnoteapp.data.remote.FakeApi
import com.example.offlinefirstnoteapp.data.remote.FakeApiImpl
import com.example.offlinefirstnoteapp.data.remote.FirestoreNoteService
import com.example.offlinefirstnoteapp.data.repositoryImpl.NotesRepositoryImplemention
import com.example.offlinefirstnoteapp.data.sync.SyncManager
import com.example.offlinefirstnoteapp.domain.NotesRepository
import com.example.offlinefirstnoteapp.usecase.GetNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesRepository(
        dao: NoteDao,
        fakApi:FakeApi,
        syncManager: SyncManager):NotesRepository = NotesRepositoryImplemention(dao,fakApi,syncManager)

    @Provides
    fun getNoteUseCase(repository: NotesRepository):GetNoteUseCase = GetNoteUseCase(repository)

   @Provides
   fun provideFakeApi():FakeApi = FakeApiImpl()

    @Provides
    @Singleton
    fun provideFireNoteService():FirestoreNoteService = FirestoreNoteService

    @Provides
    @Singleton
    fun provideDispatcher():CoroutineDispatcher = Dispatchers.IO


}