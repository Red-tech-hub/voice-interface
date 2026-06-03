/**
 * Database service — wraps expo-sqlite.
 *
 * Stores conversation history, message threads, canvas state, and app settings.
 */

// Stub: will be implemented in EPIC-PERSISTENCE
export async function initialize(): Promise<void> {
  throw new Error('Database not yet implemented');
}

export async function getConversations(): Promise<unknown[]> {
  throw new Error('Database not yet implemented');
}

export async function saveMessage(
  conversationId: string,
  role: string,
  content: string,
): Promise<void> {
  throw new Error('Database not yet implemented');
}
