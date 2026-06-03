# Architecture Decision: Voice-Assisted Agent Application

**Status:** Decided (2026-06-03)
**Decision-maker:** CTO
**Issue:** [VOI-2](/VOI/issues/VOI-2)
**Plan:** [VOI-2 plan document](/VOI/issues/VOI-2#document-plan)

## Decision Summary

**Tech stack: Expo (React Native) + Expo Dev Client**

Expo SDK 56 with Dev Client for native module access (Porcupine, on-device LLM inference).

## Stack

### Language & UI Framework
- **TypeScript 5.x** — type-safe, modern JS
- **Expo SDK 56** — managed React Native with Dev Client for native modules
- **Expo Router** — file-based routing
- **React Navigation** — navigation primitives

### Voice Layer
- **STT**: `expo-speech-recognition` v56 — wraps native Android `SpeechRecognizer` (on-device capable), iOS `SFSpeechRecognizer`, Web Speech API
- **TTS**: `expo-speech` v56 — platform-native text-to-speech (Android, iOS, Web)
- **Wake Word**: `@picovoice/porcupine-react-native` v4 — on-device, cross-platform, sub-100ms latency (requires Dev Client)
- **Audio I/O**: `expo-audio` — recording and playback

### Agent LLM
- **Primary (v1)**: OpenAI-compatible API (cloud) — configurable backend
- **On-device (v2)**: MiniCPM-V-4.6 via llama.cpp / MLC-LLM — ~1.3B params, Q4 quantized (~800MB). Benchmark before committing; consider smaller fallback (e.g., Qwen2.5-0.5B) if latency unacceptable.

### State Management
- **Zustand** — lightweight, hook-based state management

### Data Layer
- **expo-sqlite** — SQLite for local storage (conversation history, settings, agent memory)
- **Fetch API / axios** — HTTP client for cloud agent API

### Build & CI
- **EAS Build** — Expo's cloud build service for Android APK/AAB
- **GitHub Actions** — lint (ESLint) + type check (tsc) + EAS Build trigger on PR

## Module Layout

```
voi-app/
├── app/                       # Expo Router file-based routing
│   ├── (tabs)/
│   │   ├── index.tsx          # Voice / main screen
│   │   ├── chat.tsx           # Conversation history
│   │   └── settings.tsx       # Settings
│   └── _layout.tsx            # Root layout
├── src/
│   ├── features/
│   │   ├── voice/             # Voice pipeline hooks & state
│   │   ├── agent/             # Agent engine (API client + local LLM)
│   │   └── chat/              # Chat UI components
│   ├── shared/
│   │   ├── hooks/             # Shared hooks
│   │   ├── stores/            # Zustand stores
│   │   └── utils/             # Utilities
│   └── services/
│       ├── stt.ts             # expo-speech-recognition wrapper
│       ├── tts.ts             # expo-speech wrapper
│       ├── wake-word.ts       # Porcupine wrapper
│       ├── agent.ts           # Agent client (cloud + local)
│       └── database.ts        # expo-sqlite queries
├── native/                    # Native module integrations
│   ├── llama.cpp/             # llama.cpp Android/iOS bindings (v2)
│   └── porcupine/             # Porcupine platform config
├── app.json                   # Expo config with plugins
├── eas.json                   # EAS Build config
├── tsconfig.json
└── package.json
```

## Voice Pipeline

```
[Porcupine Wake Word] → [expo-speech-recognition STT] → [Agent] → [expo-speech TTS]
    (always-on,           (Android SpeechRecognizer,      (cloud     (platform-
     on-device)            on-device capable)              or local)   native)
```

## Desktop Strategy

Expo web target as the v1 desktop baseline (zero-effort from Expo project). Web Speech API handles STT via `expo-speech-recognition` web polyfill. Evaluate Electron or Tauri wrapper when desktop becomes a priority.

## Tradeoffs Considered

| Option | Pros | Cons | Verdict |
|--------|------|------|---------|
| **Expo + RN** | JS/TS ecosystem, expo-speech-recognition (native STT), Expo web for desktop, EAS Build | Dev Client needed for native modules, RN bridge overhead | ✅ Chosen (board directive) |
| **KMP + Compose** | Native perf, direct Android voice APIs, strong types | Desktop ecosystem maturing, JVM overhead | ❌ Rejected by board |
| **Flutter + Dart** | Excellent cross-platform UI, hot reload | Weak voice SDK ecosystem, no native Android voice API access | ❌ Voice SDK gap is a dealbreaker |
| **Tauri + Capacitor** | Lightweight desktop, web tech | Immature Android story, Capacitor plugin gaps for voice | ❌ Not production-ready |

## Design Principles

1. **Voice-first** — every interaction starts with voice; UI is supportive, not primary
2. **Offline-capable** — core voice recognition works without internet (Android on-device SpeechRecognizer, Porcupine)
3. **Share logic, adapt presentation** — TypeScript services shared across Android and web; platform-specific audio via native modules
4. **Make it work, make it right, make it fast** — Phase 1 ships a working voice pipeline before optimizing latency

## Open Questions (defer to implementation)

- MiniCPM-V-4.6 mobile inference benchmark results (v2 decision gate)
- TTS voice selection strategy (system default vs custom models)
- Conversation history storage schema
- Audio pipeline format (PCM 16kHz mono as starting assumption)
