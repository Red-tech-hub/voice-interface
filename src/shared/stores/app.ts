import { create } from 'zustand';

interface AppState {
  isListening: boolean;
  isSpeaking: boolean;
  wakeWordActive: boolean;
  setIsListening: (value: boolean) => void;
  setIsSpeaking: (value: boolean) => void;
  setWakeWordActive: (value: boolean) => void;
}

export const useAppStore = create<AppState>((set) => ({
  isListening: false,
  isSpeaking: false,
  wakeWordActive: false,
  setIsListening: (isListening) => set({ isListening }),
  setIsSpeaking: (isSpeaking) => set({ isSpeaking }),
  setWakeWordActive: (wakeWordActive) => set({ wakeWordActive }),
}));
