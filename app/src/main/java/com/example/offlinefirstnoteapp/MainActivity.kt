package com.example.offlinefirstnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.offlinefirstnoteapp.presentation.NotesScreen
import com.example.offlinefirstnoteapp.presentation.NotesViewModal
import com.example.offlinefirstnoteapp.ui.theme.OfflineFirstNoteAppTheme

class MainActivity : ComponentActivity() {
    val viewModal = NotesViewModal()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OfflineFirstNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotesScreen()
                }
            }
        }
    }
}



