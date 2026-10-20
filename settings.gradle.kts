pluginManagement { repositories { google(); mavenCentral(); gradlePluginPortal() } }
dependencyResolutionManagement { repositories { google(); mavenCentral() } }
rootProject.name = "lista-de-compras"
include(":app", ":framework-core", ":android-bridge")
val framework = file(System.getenv("MEGALODONTE_ANDROID_HOME") ?: "../../framework/megalodonte-android")
require(framework.resolve("android-bridge/build.gradle.kts").isFile) {
    "Set MEGALODONTE_ANDROID_HOME to a checkout of megalodonte-android"
}
project(":framework-core").projectDir = framework.resolve("framework-core")
project(":android-bridge").projectDir = framework.resolve("android-bridge")
includeBuild(framework.resolve("../libraries/megalodonte-core"))
includeBuild(framework.resolve("../libraries/megalodonte-theme"))
includeBuild(framework.resolve("../libraries/megalodonte-reactivity-portable"))
