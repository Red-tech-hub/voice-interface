package com.voiceinterface.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.voiceinterface.Di
import com.voiceinterface.domain.VoiceService
import com.voiceinterface.presentation.VoiceUiState
import com.voiceinterface.presentation.VoiceIntent

/**
 * Android entry point. Provides the Android VoiceService implementation
 * and hosts the Compose Multiplatform UI.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Wire up the platform voice service (placeholder for now)
        Di.initialize(AndroidVoiceService())

        setContent {
            MaterialTheme {
                VoiceInterfaceApp()
            }
        }
    }
}

/**
 * Placeholder Android VoiceService — will be replaced with real
 * SpeechRecognizer + TTS + Porcupine integration.
 */
class AndroidVoiceService : VoiceService {
    override suspend fun listen(timeoutMs: Long): com.voiceinterface.domain.ListenResult {
        // TODO: Integrate Android SpeechRecognizer
        return com.voiceinterface.domain.ListenResult.Timeout
    }

    override suspend fun speak(text: String) {
        // TODO: Integrate Android TextToSpeech
    }

    override suspend fun startWakeWordDetection(onWakeWord: suspend () -> Unit) {
        // TODO: Integrate Porcupine wake word
    }

    override suspend fun stopWakeWordDetection() {
        // TODO: Release Porcupine resources
    }

    override fun isAvailable(): Boolean = true // TODO: Real availability check
}

@Composable
fun VoiceInterfaceApp() {
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
