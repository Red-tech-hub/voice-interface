package com.voiceinterface.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Shared ViewModel contract. Platform-specific ViewModels implement this.
 */
interface VoiceViewModel {
    val state: StateFlow<VoiceUiState>
    fun onUserIntent(intent: VoiceIntent)
}

data class VoiceUiState(
    val isListening: Boolean = false,
    val isSpeaking: Boolean = false,
    val transcript: String = "",
    val responseText: String = "",
    val error: String? = null,
    val isWakeWordActive: Boolean = false
)

sealed interface VoiceIntent {
    data object StartListening : VoiceIntent
    data object StopListening : VoiceIntent
    data class UserSpoke(val text: String) : VoiceIntent
    data object ToggleWakeWord : VoiceIntent
}
