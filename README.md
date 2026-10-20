# Lista de Compras

Megalodonte Android application using **jsonc** UI. The app targets Android 15 (API 35).
Its screen uses only Megalodonte components; JSONC is rendered by the Android bridge.
The [Android JSONC guide](https://github.com/eliezer-dev-software-enginner/megalodonte-android/blob/main/docs/JSONC_ANDROID.md)
lists the supported component types, bindings and platform-specific properties.

Install JDK 25 and Android SDK Platform 35. Clone `megalodonte-android`,
`megalodonte-core`, `megalodonte-theme`, and `megalodonte-reactivity-portable`
under this project's `framework/` directory, or set
`MEGALODONTE_ANDROID_HOME` to the Android framework checkout. Then run:

```sh
./gradlew :app:assembleDebug :app:lintDebug
```

The APK is `app/build/outputs/apk/debug/app-debug.apk`. Open this directory in
Android Studio to run on an Android 15 device or emulator. If its editor shows
an import or constructor error while Gradle builds successfully, use **Sync
Project with Gradle Files** to refresh the local framework modules.

## Example behavior

Add shopping items, mark them complete by number, and remove them. Items persist on the device with SharedPreferences.

Enter an item and tap Add. To change or remove an item, enter its displayed number and tap the corresponding button.

The project was generated with `megalodonte-cli --platform android --mode jsonc` and then completed as a functional sample. Run `./gradlew :app:assembleDebug :app:lintDebug :app:check` to validate it. The UI is native Android through Megalodonte; no WebView is used.
