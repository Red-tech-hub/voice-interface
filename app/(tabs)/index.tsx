import { View, Text, StyleSheet } from 'react-native';

export default function VoiceScreen() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Voice Interface</Text>
      <Text style={styles.subtitle}>Tap to talk or say ‘Hey VOI’</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#E6F4FE',
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#1a1a2e',
  },
  subtitle: {
    fontSize: 16,
    color: '#555',
    marginTop: 8,
  },
});
