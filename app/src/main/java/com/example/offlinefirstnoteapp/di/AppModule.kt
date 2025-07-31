package com.example.offlinefirstnoteapp.di

import com.example.offlinefirstnoteapp.data.local.NoteDao
import com.example.offlinefirstnoteapp.data.remote.FakeApi
import com.example.offlinefirstnoteapp.data.remote.FakeApiImpl
import com.example.offlinefirstnoteapp.data.repositoryImpl.NotesRepositoryImplemention
import com.example.offlinefirstnoteapp.domain.NotesRepository
import com.example.offlinefirstnoteapp.usecase.GetNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesRepository(
        dao: NoteDao,
        fakApi:FakeApi):NotesRepository = NotesRepositoryImplemention(dao,fakApi)

    @Provides
    fun getNoteUseCase(repository: NotesRepository):GetNoteUseCase = GetNoteUseCase(repository)

   @Provides
   fun provideFakeApi():FakeApi = FakeApiImpl()


}