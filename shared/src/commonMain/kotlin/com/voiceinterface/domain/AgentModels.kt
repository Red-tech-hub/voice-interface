package com.voiceinterface.domain

/**
 * Represents an agent response: text to speak plus optional display content.
 */
data class AgentResponse(
    val text: String,
    val displayText: String? = null,
    val actions: List<AgentAction> = emptyList()
)

sealed interface AgentAction {
    data class SetReminder(val text: String, val timeMillis: Long) : AgentAction
    data class CreateNote(val text: String) : AgentAction
    data object Unknown : AgentAction
}
