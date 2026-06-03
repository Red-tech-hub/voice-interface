# PRODUCT.md — Voice Interface Agent

## Product Vision

**Vox** is a voice-first AI assistant that lives on your phone and your computer. You talk to it like a person — it listens, thinks, and responds in natural speech. No screens required, but screens enhance when you want them.

Vox handles the full loop: wake word → speech recognition → agent reasoning → speech synthesis. It runs core inference on-device for privacy and low latency, with optional cloud fallback for complex queries.

## What Vox Does

1. **Conversational Q&A** — Ask anything. Follow-ups, clarifications, deep dives. Like talking to a knowledgeable friend.
2. **Task & schedule management** — Create reminders, todos, and calendar events by voice. "Remind me to call Mom at 6" just works.
3. **Device control** — Adjust volume, brightness, toggle settings, launch apps. Your voice is the remote control.
4. **Note-taking & dictation** — Capture ideas hands-free. Transcribe meetings. Summarize conversations.
5. **Cross-device continuity** — Start a conversation on your phone, pick it up on your desktop. Context follows you.

## Target Platforms

| Platform | Priority | Notes |
|----------|----------|-------|
| Android 14+ | **Tier 1** | Primary mobile target. Kotlin/Compose. Background service for always-on listening. |
| Desktop (Windows 10+, macOS 13+, Linux) | **Tier 1** | Electron-based. System tray + global hotkey. |
| iOS | Future | Not in initial scope. Requires Swift/SwiftUI rewrite. |
| Web | Future | Not in initial scope. Browser limitations on wake-word and background audio. |

## Key User Flows

### Flow 1: Wake and Ask
```
User: "Hey Vox, what's the weather today?"
Vox: [wake word detected] → [STT: "what's the weather today?"]
     → [Agent: weather_query(location=current)] → [TTS: "It's 72°F and sunny in San Francisco."]
```
**Latency target**: < 500ms from end of speech to first audio byte of response.

### Flow 2: Task Creation
```
User: "Hey Vox, remind me to buy milk at 5pm"
Vox: [STT] → [Agent: create_reminder(text="buy milk", time=17:00)]
     → [TTS: "Reminder set for 5pm."]
     → [System notification scheduled]
```

### Flow 3: Cross-Device Handoff
```
Phone: User asks Vox to draft an email → Vox starts draft
Desktop: User sits down → Vox detects proximity → "Want me to finish that email on the big screen?"
User: "Yes" → Desktop Vox opens with draft context loaded
```

### Flow 4: Continuous Conversation
```
User: "Hey Vox, find Italian restaurants nearby"
Vox: "I found three. Trattoria Contadina is highest rated."
User: "What's their phone number?"  [no wake word needed — in active session]
Vox: "(415) 555-0123. Want me to call them?"
User: "Yes please."
Vox: [dials]
```

## Design Principles

1. **Voice-first, not voice-only** — Voice is the primary interface. Screens augment, they don't replace.
2. **Ambient, not invasive** — Vox is there when you need it, invisible when you don't.
3. **Fast feels smart** — Sub-second response times are not a nice-to-have. They define the experience.
4. **Privacy by default** — On-device STT and TTS. Agent inference on-device for common queries. Cloud only when needed and consented.
5. **One product, two screens** — Android and desktop share agent logic, conversation history, and settings. Only the presentation layer adapts.

---

## 3-Month Technical Roadmap

### Month 1: Foundation (Weeks 1-4)
**Goal**: Core voice pipeline working end-to-end on a development machine.

| Week | Milestone | Deliverable |
|------|-----------|-------------|
| 1 | Architecture & stack decided | ARCHITECTURE.md approved. Repo scaffold with CI. |
| 2 | Voice pipeline: wake word + STT | Wake word model integrated. STT engine selected and running. |
| 3 | Voice pipeline: Agent + TTS | LLM agent with tool-use capability. TTS engine integrated. |
| 4 | End-to-end demo | "Hey Vox, what's the weather?" works on dev machine. Audio in, audio out. |

**Epics (Month 1)**:
- **EPIC-ARCH**: Architecture & tech stack — finalize languages, frameworks, voice SDK, data layer
- **EPIC-SCAFFOLD**: Project scaffold — monorepo setup, CI/CD, linting, testing framework
- **EPIC-WAKE**: Wake word detection — evaluate models (Porcupine, OpenWakeWord), integrate, benchmark
- **EPIC-STT**: Speech-to-text — evaluate engines (Whisper, Sherpa-ONNX), integrate, benchmark latency/accuracy
- **EPIC-AGENT**: Agent core — LLM selection, tool-use framework, conversation management, context window
- **EPIC-TTS**: Text-to-speech — evaluate engines (Piper, Coqui, Edge TTS), integrate, benchmark quality/latency

### Month 2: Platforms (Weeks 5-8)
**Goal**: Vox running on Android and desktop with basic UI.

| Week | Milestone | Deliverable |
|------|-----------|-------------|
| 5-6 | Android MVP | Vox runs as Android app with background service, wake word, basic chat UI |
| 7-8 | Desktop MVP | Vox runs as Electron app with system tray, global hotkey, basic chat UI |

**Epics (Month 2)**:
- **EPIC-ANDROID**: Android app — Kotlin/Compose UI, background audio service, platform permissions, notification integration
- **EPIC-DESKTOP**: Desktop app — Electron/Tauri shell, tray integration, global hotkey, auto-start
- **EPIC-CONVERSATION**: Conversation UI — shared chat component, message rendering (text + audio), session management

### Month 3: Integration & Polish (Weeks 9-12)
**Goal**: Cross-device sync, quality hardening, first alpha release.

| Week | Milestone | Deliverable |
|------|-----------|-------------|
| 9-10 | Cross-device sync | Conversation history and settings sync between Android and desktop |
| 11 | Testing & bug fixing | Test suite passing. Critical bugs resolved. Performance benchmarks met. |
| 12 | Alpha release | Packaged builds for Android (.apk) and desktop (.dmg/.exe/.AppImage). Internal dogfooding begins. |

**Epics (Month 3)**:
- **EPIC-SYNC**: Cross-device sync — local network device discovery, encrypted state sync, session handoff
- **EPIC-TEST**: Test infrastructure — unit tests, integration tests, voice pipeline E2E tests, CI enforcement
- **EPIC-PACKAGE**: Packaging & distribution — Android APK signing, desktop installers, auto-update mechanism
- **EPIC-PERF**: Performance — latency benchmarks, memory profiling, battery impact on Android, SLO dashboard

---

## Explicitly Out of Scope (First 3 Months)

| Area | Reason | When |
|------|--------|------|
| iOS app | Requires separate Swift/SwiftUI codebase. Android first. | Month 4+ |
| Web client | Browser audio constraints make wake-word unreliable. | Month 6+ |
| Smart home integration | Adds protocol surface (Matter, HomeKit) before core is stable. | Month 6+ |
| Third-party plugin system | Premature extensibility. Ship a great built-in experience first. | Month 9+ |
| Multi-language support | English only for MVP. i18n adds surface to every component. | Month 4+ |
| Cloud sync service | On-device/local-network only initially. Cloud adds auth, infra, cost. | Month 6+ |
| Custom wake word training | "Hey Vox" only. Custom wake words are a delight feature, not launch-critical. | Month 6+ |
| Voice biometrics / speaker ID | Complex, privacy-sensitive. Single-user MVP first. | Month 9+ |

---

## Success Metrics (Alpha)

| Metric | Target |
|--------|--------|
| Wake word accuracy | > 95% in quiet, > 85% in noise |
| STT word error rate | < 5% on clean speech |
| End-to-end latency (speech end → first audio) | < 500ms p50, < 1000ms p95 |
| TTS mean opinion score | > 3.5 / 5 |
| Android battery impact (1hr active listening) | < 5% battery |
| Desktop memory footprint (idle) | < 200MB |
| Cross-device sync latency | < 2s for session handoff |

---

## Risks & Mitigations

| Risk | Impact | Mitigation |
|------|--------|------------|
| On-device LLM too slow/weak | Blocks core value prop | Cloud fallback path designed from day one. Hybrid inference architecture. |
| Android background audio restrictions | Wake word unreliable when app backgrounded | Foreground service with persistent notification. Test early on diverse OEMs. |
| Cross-platform audio stack divergence | Double the platform-specific work | Abstract audio I/O behind a common interface. Test on both platforms weekly. |
| Wake word false positives | User frustration, battery drain | Two-stage detection (small model always-on → larger model verification). User-adjustable sensitivity. |
| Team capacity (currently 2 agents) | Slow progress | Hire founding engineer in Month 1. Add coder capacity in Month 2. |

---

## Team & Hiring Plan

Aligned with [VOI-1 plan](/VOI/issues/VOI-1#document-plan):

| Role | When | Reports To |
|------|------|------------|
| CTO (filled) | Now | CEO |
| Founding Engineer | Month 1, Week 1 | CTO |
| UX Designer | Month 2 | CEO |
| Coder (additional) | Month 2 | CTO |
| QA Engineer | Month 3 | CTO |
| Security Engineer | Month 3 | CEO |
