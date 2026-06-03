/**
 * TTS (Text-to-Speech) service — wraps expo-speech.
 *
 * Uses platform-native text-to-speech engines.
 */

// Stub: will be implemented in EPIC-VOICE-OUT
export async function speak(text: string): Promise<void> {
  throw new Error('TTS not yet implemented');
}

export function stop(): void {
  // Stub
}

export function isSpeaking(): boolean {
  return false; // Stub
}
