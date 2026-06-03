# Project: Onboarding

## Goal

Set up the engineering foundation for the voice-assisted agent application:
architecture, project scaffold, CI pipeline, and team hiring.

## Status

- [x] [VOI-1](/VOI/issues/VOI-1): Hire first engineer + hiring plan
- [x] [VOI-2](/VOI/issues/VOI-2): Define technical architecture
- [x] [VOI-13](/VOI/issues/VOI-13): Set up Expo project scaffold replacing KMP structure
- [ ] [VOI-14](/VOI/issues/VOI-14): EPIC-CHAT — Build chat interface with message list and text input
- [ ] [VOI-15](/VOI/issues/VOI-15): EPIC-VOICE-OUT — Integrate text-to-speech with expo-speech
- [ ] [VOI-16](/VOI/issues/VOI-16): EPIC-VOICE-IN — Integrate speech-to-text with expo-speech-recognition
- [ ] [VOI-17](/VOI/issues/VOI-17): EPIC-AGENT — Implement OpenAI-compatible agent client with streaming
- [ ] [VOI-18](/VOI/issues/VOI-18): EPIC-WAKE — Integrate Porcupine wake word detection
- [ ] [VOI-19](/VOI/issues/VOI-19): EPIC-BUILD — Configure EAS Build and web deployment pipeline

## Architecture

See [ARCHITECTURE.md](./ARCHITECTURE.md) for the full tech stack decision.

**Key decisions:**
- Expo SDK 56 (React Native) + Expo Dev Client
- Expo Router for file-based routing
- Voice pipeline: Porcupine → expo-speech-recognition → Agent → expo-speech
- Zustand for state management, expo-sqlite for persistence
- Cross-platform: Android + Web (desktop)

## Conventions

- File-based routing via Expo Router (`app/` directory)
- Feature modules isolated under `src/features/`
- Shared code in `src/shared/` (hooks, stores, utils)
- Service wrappers in `src/services/` (stt, tts, wake-word, agent, database)
- Native module integrations in `native/`
- Voice-first design: voice is primary input modality
- Offline-capable: core speech recognition works without internet
