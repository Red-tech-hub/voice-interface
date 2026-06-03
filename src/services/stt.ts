/**
 * STT (Speech-to-Text) service — wraps expo-speech-recognition.
 *
 * Provides platform-native speech recognition:
 * - Android: SpeechRecognizer (on-device capable)
 * - iOS: SFSpeechRecognizer
 * - Web: Web Speech API
 */

// Stub: will be implemented in EPIC-VOICE-IN
export async function startListening(): Promise<void> {
  throw new Error('STT not yet implemented');
}

export async function stopListening(): Promise<string> {
  throw new Error('STT not yet implemented');
}

export function isAvailable(): boolean {
  return false; // Stub
}
