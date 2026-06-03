package com.voiceinterface.desktop

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.voiceinterface.Di
import com.voiceinterface.presentation.VoiceUiState
import com.voiceinterface.presentation.VoiceIntent

fun main() = application {
    val windowState = rememberWindowState(
        size = DpSize(400.dp, 600.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "VOI - Voice Interface",
        state = windowState
    ) {
        MaterialTheme {
            VoiceInterfaceContent()
        }
    }
}

@Composable
fun VoiceInterfaceContent() {
    var uiState by remember { mutableStateOf(VoiceUiState()) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "VOI",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (uiState.isListening) {
                Text(
                    text = "Listening...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            } else if (uiState.transcript.isNotEmpty()) {
                Text(
                    text = uiState.transcript,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Text(
                    text = "Tap to speak",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (uiState.error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = uiState.error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
