# PRODUCT.md — Voice Interface

## Product Vision

**Voice Interface** is a voice-first AI assistant wrapped in a chat application. You talk to it like a person — it listens, reasons, and responds. The conversation lives in a chat interface where you can also type, scroll back through history, and open a shared canvas for visual thinking.

Voice is the primary input. Chat is the home screen. Canvas is the tool you reach for when a picture is worth a thousand words.

## What It Does

1. **Voice Assistant** — Wake-word activated ("Hey VOI") or tap-to-talk. Natural conversation. The agent answers questions, performs tasks, and collaborates. Voice is the primary interaction mode.
2. **Chat App** — Every conversation is a chat thread. You see the full history. You can type when voice isn't convenient. The chat is always there — it's the home screen.
3. **Excalidraw Canvas** — Open a shared whiteboard from within any chat. The agent can draw diagrams, flowcharts, wireframes, and sketches. You edit them. Canvas artifacts appear inline in the chat.

## Target Platforms

| Platform | Priority | Notes |
|----------|----------|-------|
| Android | **Tier 1** | Expo (React Native) via EAS Build. Background service for wake word. |
| Desktop (Web) | **Tier 1** | Expo web target. System tray + global hotkey for voice activation. |
| iOS | Follows | Expo supports iOS natively. Ship after Android validation. |

## Key User Flows

### Flow 1: Wake and Ask (Voice-First)
```
User: "Hey VOI, what's the weather today?"
VOI: [wake word → STT → agent → TTS] "It's 72°F and sunny."
     [Chat thread shows both messages as text bubbles]
```

### Flow 2: Chat Conversation
```
User opens app → sees chat thread with conversation history
User types: "Remind me to call Mom at 6pm"
VOI responds in chat + audio: "Reminder set for 6pm."
User scrolls up to see earlier conversations
```

### Flow 3: Open Canvas from Chat
```
User: "Hey VOI, draw a system architecture diagram for our app"
VOI: "Here's a starting diagram." → Canvas opens with boxes and arrows
User edits on canvas — moves, resizes, adds labels
Canvas saved as part of the chat thread
User: "Add authentication flow" → Agent updates canvas
```

### Flow 4: Canvas-Driven Conversation
```
User opens canvas, sketches a rough flowchart
User: "Hey VOI, turn this into a clean swimlane diagram"
VOI reads canvas → regenerates polished version
User: "What does the error handling path look like?"
VOI: "Let me highlight it." → Annotates on canvas + explains in chat
```

### Flow 5: Cross-Device
```
Phone: "Hey VOI, start drafting an email about the Q3 roadmap"
Desktop: User sits down → chat thread synced → continues conversation
Canvas state preserved across devices
```

## Design Principles

1. **Voice-first, not voice-only** — Voice is the primary input. But the chat is always visible, and typing always works. Canvas is one tap away.
2. **Chat is the home screen** — Open the app, you see your conversations. Voice, text, and canvas artifacts in one scrollable timeline.
3. **Canvas is a feature, not a mode** — It opens when needed, not by default. Diagrams appear inline in the chat when closed.
4. **Fast feels smart** — Sub-second wake word detection. Streaming responses. Smooth canvas interactions on mobile.
5. **Cross-platform from day one** — One Expo codebase for Android and web desktop.

---

## 3-Month Technical Roadmap

### Month 1: Voice + Chat Core (Weeks 1-4)
**Goal**: Voice assistant working end-to-end with a chat UI. Wake word, STT, agent, TTS.

| Week | Milestone | Deliverable |
|------|-----------|-------------|
| 1 | Scaffold + chat shell | Expo project with Expo Router, chat UI with message list (text only). Agent connected to OpenAI-compatible API. |
| 2 | Voice input (STT) | expo-speech-recognition integrated. Tap-to-talk. Voice messages appear in chat. |
| 3 | Wake word + TTS output | Porcupine wake word ("Hey VOI"). expo-speech TTS for agent responses. Always-on listening on Android. |
| 4 | Android + Web builds | EAS Build producing APK. Web deploy to staging. Full voice pipeline working end-to-end. |

**Epics (Month 1)**:
- **EPIC-SCAFFOLD**: Expo project setup — Expo Router, Zustand stores, ESLint, GitHub Actions CI. Replace KMP scaffold.
- **EPIC-CHAT**: Chat interface — message list, text input, streaming responses, markdown rendering, conversation threads
- **EPIC-VOICE-IN**: Voice-to-text — expo-speech-recognition, mic permissions, tap-to-talk, audio visualization
- **EPIC-WAKE**: Wake word — Porcupine integration ("Hey VOI"), background audio service (Android), sensitivity tuning
- **EPIC-VOICE-OUT**: Text-to-speech — expo-speech, streaming TTS, voice selection
- **EPIC-AGENT**: Agent integration — OpenAI-compatible API, conversation context, system prompt, tool definitions
- **EPIC-BUILD**: Build pipeline — EAS Build for Android, web deploy, CI integration

### Month 2: Canvas (Weeks 5-8)
**Goal**: Excalidraw canvas integrated. Agent can create and modify diagrams. Chat and canvas are linked.

| Week | Milestone | Deliverable |
|------|-----------|-------------|
| 5 | Canvas component | Excalidraw embedded (WebView on native, web component on desktop). Draw, pan, zoom, export. |
| 6 | Agent → Canvas | Agent generates diagrams from voice/chat commands. Structured output (Mermaid/JSON) → canvas render. |
| 7 | Canvas → Chat | Canvas snapshots appear inline in chat. Agent reads canvas state for context. |
| 8 | Polish | Touch-friendly canvas on mobile. Smooth transitions. Canvas persists per conversation. |

**Epics (Month 2)**:
- **EPIC-CANVAS**: Drawing canvas — Excalidraw integration, touch/pointer support, toolbar, export PNG/SVG
- **EPIC-AGENT-DRAW**: Agent drawing — structured diagram generation (flowcharts, architecture diagrams, mind maps), Mermaid/JSON interchange
- **EPIC-CANVAS-CHAT**: Chat-canvas linking — inline canvas previews in chat, canvas state as agent context, shared whiteboard mode

### Month 3: Polish & Release (Weeks 9-12)
**Goal**: Production quality. Conversation persistence. Alpha release.

| Week | Milestone | Deliverable |
|------|-----------|-------------|
| 9 | Conversation persistence | Chat history + canvas state saved to expo-sqlite. Resume sessions. Search. |
| 10 | UX polish | Animations, error states, loading states, empty states, dark mode, responsive layout. |
| 11 | Testing & hardening | E2E tests. Performance profiling (wake word latency, TTS quality, canvas perf). Bug bash. |
| 12 | Alpha release | APK on GitHub Releases. Web on staging. Feedback loop open. |

**Epics (Month 3)**:
- **EPIC-PERSISTENCE**: Local storage — expo-sqlite schema for conversations, messages, canvas snapshots, settings
- **EPIC-UX**: UX polish — skeletons, error boundaries, offline states, keyboard handling, dark mode, accessibility
- **EPIC-TEST**: Test infrastructure — Vitest unit tests, Playwright/Detox E2E for voice + chat + canvas flows
- **EPIC-RELEASE**: Release — APK signing, web deployment, changelog, crash reporting, feedback collection

---

## Explicitly Out of Scope (First 3 Months)

| Area | Reason | When |
|------|--------|------|
| Custom wake word training | "Hey VOI" only. Customization is a nice-to-have. | Month 4+ |
| Multi-user / collaboration | Single-user. Real-time collaboration needs CRDT/OT + auth. | Month 6+ |
| File upload / image import | Canvas is agent-generated + user-drawn. Import adds complexity. | Month 4+ |
| Plugin system | Premature extensibility. Ship a great built-in experience. | Month 9+ |
| Offline agent (on-device LLM) | Cloud LLM for v1. MiniCPM-V-4.6 benchmarked and deferred. | Month 6+ |
| Multi-language | English only for alpha. | Month 4+ |
| Smart home / device control | Outside core scope of chat + canvas. | Month 9+ |
| Voice biometrics / speaker ID | Privacy-sensitive, single-user MVP first. | Month 9+ |

---

## Success Metrics (Alpha)

| Metric | Target |
|--------|--------|
| Wake word detection latency | < 100ms |
| Wake word accuracy (quiet / noise) | > 95% / > 85% |
| STT word accuracy | > 90% |
| E2E voice latency (speech end → first TTS byte) | < 1s p50 |
| Chat response latency (first token) | < 2s p50 |
| Canvas render (complex diagram) | < 1s on device |
| App cold start | < 3s on mid-range Android |
| APK size | < 60MB |
| Crash-free session rate | > 99% |

---

## Risks & Mitigations

| Risk | Impact | Mitigation |
|------|--------|------------|
| Excalidraw not React Native compatible | Canvas blocked on mobile | WebView approach on native. Excalidraw React component on web. Test early. |
| Wake word false positives | User frustration, battery | Two-stage detection. User-adjustable sensitivity. Visual indicator when active. |
| Android background audio restrictions | Wake word killed by OS | Foreground service with persistent notification. Test on Samsung, Xiaomi, Pixel. |
| Agent diagram quality | Generated diagrams look bad | Constrain to structured formats (Mermaid). Few-shot prompting. User editing as fallback. |
| Team capacity (2 agents) | Slow progress | Hire coders in Month 1. CTO focuses on architecture and review. |
| TTS quality on Expo | Robotic voice | expo-speech uses platform-native TTS (good on Android). Evaluate cloud TTS fallback. |

---

## Team & Hiring Plan

| Role | When | Reports To |
|------|------|------------|
| CTO (filled) | Now | CEO |
| Coder — Chat + Voice Pipeline | Month 1, Week 1 | CTO |
| Coder — Canvas Integration | Month 2, Week 1 | CTO |
| UX Designer | Month 2 | CEO |
| QA Engineer | Month 3 | CTO |
