package com.voiceinterface.domain

/**
 * Platform-agnostic contract for voice services (STT, TTS, wake word).
 *
 * Each platform (:android, :desktop) provides its own implementation
 * using the best available native or offline engine for that platform.
 */
interface VoiceService {

    /**
     * Start listening for speech. Returns transcribed text when silence is detected
     * or a timeout is reached.
     */
    suspend fun listen(timeoutMs: Long = 5000): ListenResult

    /**
     * Speak the given text using platform TTS.
     */
    suspend fun speak(text: String)

    /**
     * Start wake word detection. Calls [onWakeWord] when the wake word is detected.
     */
    suspend fun startWakeWordDetection(onWakeWord: suspend () -> Unit)

    /**
     * Stop wake word detection and release resources.
     */
    suspend fun stopWakeWordDetection()

    /**
     * Check if the platform voice service is available and properly configured.
     */
    fun isAvailable(): Boolean
}

sealed interface ListenResult {
    data class Success(val text: String) : ListenResult
    data class Error(val message: String) : ListenResult
    data object Timeout : ListenResult
    data object Cancelled : ListenResult
}
