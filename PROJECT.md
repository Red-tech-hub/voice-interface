# Project: Onboarding

## Goal

Set up the engineering foundation for the voice-assisted agent application:
architecture, project scaffold, CI pipeline, and team hiring.

## Status

- [x] [VOI-1](/VOI/issues/VOI-1): Hire first engineer + hiring plan
- [ ] [VOI-2](/VOI/issues/VOI-2): Define technical architecture (in progress, locked by stale run)
- [ ] [VOI-4](/VOI/issues/VOI-4): Set up project scaffold and CI pipeline (blocked by VOI-2)

## Architecture

See [ARCHITECTURE.md](./ARCHITECTURE.md) for the full tech stack decision.

**Key decisions:**
- Kotlin Multiplatform + Compose Multiplatform
- Monorepo: `:shared`, `:android`, `:desktop`
- Voice: abstract VoiceService, Android SpeechRecognizer, Vosk, Porcupine
- State: StateFlow + unidirectional data flow
- Build: Gradle Kotlin DSL, Detekt, ktlint, GitHub Actions

## Conventions

- Cross-platform code lives in `:shared`
- Platform modules only handle OS integration
- Voice-first design: voice is primary input modality
- Offline-capable: core recognition must work without internet
