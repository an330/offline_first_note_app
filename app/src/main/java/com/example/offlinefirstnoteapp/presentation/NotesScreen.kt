package com.example.offlinefirstnoteapp.presentation

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.offlinefirstnoteapp.domain.Note
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(viewModel: NotesViewModal) {
    val notes = viewModel.notes.collectAsState().value
    var noteContent = remember {  mutableStateOf("")}
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = noteContent.value,
            onValueChange = {noteContent.value = it},
            textStyle = LocalTextStyle.current

        )


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(noteContent.value.isNotBlank()) {
                    val notes = Note(
                        title = "untitled",
                        content = noteContent.value,
                        isSynced = false
                    )


                    viewModel.addNotes(notes)
                }
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sync Notes")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Saved Notes", style = MaterialTheme.typography.titleMedium)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(notes, key ={it.id}){note ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if(it==SwipeToDismissBoxValue.EndToStart){
                            coroutineScope.launch { viewModel.deleteNote(note) }
                            true
                        }

                        else false
                    }

                )
                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                                .background(MaterialTheme.colorScheme.error),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    },
                    content = {
                        Card(modifier = Modifier.fillParentMaxWidth()
                            .padding(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (note.isSynced) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.errorContainer
                            )) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = note.title, style = MaterialTheme.typography.titleSmall)
                                Text(text = note.content)
                                Text(
                                    text = if (note.isSynced) "Synced" else "Not Synced",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                )


            }
        }

    }
}
