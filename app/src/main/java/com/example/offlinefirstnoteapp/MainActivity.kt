package com.example.offlinefirstnoteapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.offlinefirstnoteapp.presentation.NotesScreen
import com.example.offlinefirstnoteapp.ui.theme.OfflineFirstNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OfflineFirstNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NotesScreen(viewModel = hiltViewModel())
                }
            }
        }
    }
}
