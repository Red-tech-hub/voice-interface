/**
 * Wake word detection service — wraps @picovoice/porcupine-react-native.
 *
 * On-device wake word detection with sub-100ms latency.
 * Detects "Hey VOI" to activate the voice pipeline.
 */

// Stub: will be implemented in EPIC-WAKE
export async function initialize(): Promise<void> {
  throw new Error('Wake word not yet implemented');
}

export async function start(): Promise<void> {
  throw new Error('Wake word not yet implemented');
}

export async function stop(): Promise<void> {
  // Stub
}

export function addListener(callback: () => void): () => void {
  // Stub: returns unsubscribe function
  return () => {};
}
