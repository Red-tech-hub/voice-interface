package com.voiceinterface

import com.voiceinterface.domain.VoiceService

/**
 * Central dependency injection module for shared code.
 *
 * Platform modules provide concrete implementations of platform-specific
 * interfaces (e.g., VoiceService) and register them here.
 */
object Di {
    lateinit var voiceService: VoiceService
        private set

    fun initialize(voiceService: VoiceService) {
        this.voiceService = voiceService
    }
}
