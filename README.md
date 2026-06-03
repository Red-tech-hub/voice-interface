# Voice Interface (VOI)

Voice-first AI assistant with chat and Excalidraw canvas. Built with Expo SDK 56 (React Native).

## Stack

- **Framework**: Expo SDK 56 (React Native) with Expo Router
- **Voice**: expo-speech-recognition (STT), expo-speech (TTS), Porcupine wake word
- **State**: Zustand
- **Storage**: expo-sqlite
- **CI**: GitHub Actions (lint + typecheck + test)

## Getting Started

```bash
# Install dependencies
npm install

# Start development server
npm start

# Run on Android
npm run android

# Run on web
npm run web
```

## Project Structure

```
voi-app/
├── app/                    # Expo Router (file-based routing)
│   ├── (tabs)/
│   │   ├── index.tsx       # Voice / main screen
│   │   ├── chat.tsx        # Conversation history
│   │   └── settings.tsx    # Settings
│   └── _layout.tsx         # Root layout
├── src/
│   ├── features/
│   │   ├── voice/          # Voice pipeline hooks & state
│   │   ├── agent/          # Agent engine (API client + local LLM)
│   │   └── chat/           # Chat UI components
│   ├── shared/
│   │   ├── hooks/          # Shared hooks
│   │   ├── stores/         # Zustand stores
│   │   └── utils/          # Utilities
│   └── services/
│       ├── stt.ts          # Speech-to-text wrapper
│       ├── tts.ts          # Text-to-speech wrapper
│       ├── wake-word.ts    # Porcupine wrapper
│       ├── agent.ts        # Agent client (cloud + local)
│       └── database.ts     # SQLite queries
├── native/                 # Native module placeholders
│   ├── llama.cpp/          # On-device LLM (v2)
│   └── porcupine/          # Porcupine platform config
├── app.json                # Expo config with plugins
├── tsconfig.json           # TypeScript strict
└── package.json
```

## Scripts

| Command | Description |
|---------|-------------|
| `npm start` | Start Expo dev server |
| `npm run android` | Run on Android |
| `npm run web` | Run on web |
| `npm run lint` | ESLint check |
| `npm run typecheck` | TypeScript type check |
| `npm test` | Vitest test suite |

## Architecture

See [ARCHITECTURE.md](./ARCHITECTURE.md) for the full technical architecture decision.

## Roadmap

See [PRODUCT.md](./PRODUCT.md) for the 3-month product roadmap and epics.

## License

Proprietary — all rights reserved.
