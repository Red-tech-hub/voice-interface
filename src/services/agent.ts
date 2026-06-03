/**
 * Agent client — communicates with the LLM backend.
 *
 * V1: OpenAI-compatible API (cloud)
 * V2: On-device LLM via llama.cpp (MiniCPM-V-4.6 or equivalent)
 */

export interface AgentMessage {
  role: 'user' | 'assistant' | 'system';
  content: string;
}

export interface AgentResponse {
  text: string;
  toolCalls?: unknown[];
}

// Stub: will be implemented in EPIC-AGENT
export async function sendMessage(
  messages: AgentMessage[],
  systemPrompt?: string,
): Promise<AgentResponse> {
  throw new Error('Agent not yet implemented');
}

export function createSystemPrompt(): string {
  return 'You are VOI, a helpful voice assistant. Be concise and conversational.';
}
