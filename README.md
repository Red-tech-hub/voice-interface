# Voice Interface

A voice-assisted agent application that runs natively on **Android** and **desktop** (Linux, macOS, Windows), giving users a fast, private, conversational AI companion.

## Vision

VOI puts a capable AI agent at your fingertips — or rather, at your voice. Speak naturally, get things done. No cloud dependency, no subscription treadmill, no privacy tradeoffs. Your voice, your device, your data.

## Key Principles

- **Voice-first**: Audio latency, wake-word accuracy, and conversational UX are the product — not afterthoughts.
- **Cross-platform consistency**: Android and desktop share one codebase with a single logic core. Adapt presentation, never reimplement.
- **Privacy by default**: On-device processing wherever possible. Nothing leaves the device without explicit consent.
- **Ship early, learn fast**: Working software over perfect plans. Validate with real users.
- **Technical excellence without over-engineering**: Choose boring technology where it works. Reserve innovation for where it differentiates.

## Repository

```
github.com/Red-tech-hub/voice-interface
```

**Name: `voice-interface`** — created under [Red-tech-hub](https://github.com/Red-tech-hub).

## Platform Targets

| Platform | Status |
|----------|--------|
| Android  | Planned |
| Linux Desktop | Planned |
| macOS Desktop | Planned |
| Windows Desktop | Planned |

## Architecture

Decided in [VOI-2](/VOI/issues/VOI-2). Full details in [ARCHITECTURE.md](./ARCHITECTURE.md).

- **Kotlin Multiplatform + Compose Multiplatform** — shared logic core and UI across Android and Desktop
- **Monorepo: `:shared`, `:android`, `:desktop`** — Gradle modules with version catalog
- **Voice layer**: Abstract `VoiceService` → Android SpeechRecognizer + Vosk (offline) / Porcupine (wake word)
- **State**: Coroutines + StateFlow, unidirectional data flow
- **DI**: Koin, lightweight KMP-compatible
- **Data**: SQLDelight (offline-first), Kotlinx.serialization
- **Build**: Gradle Kotlin DSL, Detekt (lint), ktlint (format), GitHub Actions CI

## Project Status

| Milestone | Issue | Status |
|-----------|-------|--------|
| Hiring plan | [VOI-1](/VOI/issues/VOI-1) | ✅ Done |
| Architecture & tech stack | [VOI-2](/VOI/issues/VOI-2) | ✅ Done |
| Product vision & roadmap | [VOI-3](/VOI/issues/VOI-3) | 🔄 In progress |
| Scaffold & CI | [VOI-4](/VOI/issues/VOI-4) | 🔄 In progress |
| Repo docs & naming | [VOI-5](/VOI/issues/VOI-5) | 🔄 In progress |

## Team

- **CEO** — Strategy, product direction, hiring
- **CTO** — Technical roadmap, architecture, engineering execution

## Contributing

Contribution guidelines will be established once the scaffold is in place ([VOI-4](/VOI/issues/VOI-4)).

## License

TBD — to be determined before the first public commit.
