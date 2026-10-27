# Lista de Compras

An Android shopping list sample built with Megalodonte and a JSONC interface. Add items, mark or unmark them by their displayed number, and remove them. The list is stored on the device with Android `SharedPreferences`.

The screen is defined in [`app/src/main/assets/views/home.view.jsonc`](app/src/main/assets/views/home.view.jsonc) and rendered as native Android components by the Megalodonte bridge, without a WebView. The [Android JSONC guide](https://github.com/eliezer-dev-software-enginner/megalodonte-android/blob/main/docs/JSONC_ANDROID.md) describes supported components, bindings, and platform properties.

## Requirements

- JDK 25 and Android SDK Platform 35. The project targets Android 15 (API 35) and has a minimum SDK of 35.
- Checkouts of `megalodonte-android`, `megalodonte-core`, `megalodonte-theme`, and `megalodonte-reactivity-portable`. These are separate projects and are not included here.

Place the checkouts in this layout relative to this repository:

```text
framework/
├── megalodonte-android/
└── libraries/
    ├── megalodonte-core/
    ├── megalodonte-theme/
    └── megalodonte-reactivity-portable/
```

By default, Gradle looks for `framework/` two directories above this repository. Alternatively, set `MEGALODONTE_ANDROID_HOME` to the `megalodonte-android` checkout; the three library checkouts must then be in a sibling `libraries/` directory. See [`settings.gradle.kts`](settings.gradle.kts) for the exact paths.

## Build and run

From the repository root, run:

```sh
./gradlew :app:assembleDebug :app:lintDebug :app:check
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`. Open the project in Android Studio to run it on an Android 15 device or emulator. If the editor reports an import or constructor error while Gradle builds successfully, use **Sync Project with Gradle Files** to refresh the local framework modules.

## Use

Enter an item in **Novo item** and tap **Adicionar item**. To mark or unmark an item, enter its displayed number in **Número do item** and tap **Marcar / desmarcar**. Use **Remover item** with the displayed number to delete it.

This project was generated with `megalodonte-cli --platform android --mode jsonc` and then completed as a functional sample.
