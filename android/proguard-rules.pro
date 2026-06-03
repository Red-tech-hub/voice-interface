# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in the Android SDK tools proguard defaults.

# Kotlin serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class com.voiceinterface.**$$serializer { *; }
-keepclassmembers class com.voiceinterface.** {
    *** Companion;
}
-keepclasseswithmembers class com.voiceinterface.** {
    kotlinx.serialization.KSerializer serializer(...);
}
