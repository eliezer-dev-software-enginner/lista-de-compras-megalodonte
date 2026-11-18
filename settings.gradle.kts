pluginManagement { repositories { google(); mavenCentral(); gradlePluginPortal() } }
dependencyResolutionManagement { repositories { google(); mavenCentral() } }
rootProject.name = "lista-de-compras"
include(":app", ":megalodonte-base-android", ":megalodonte-bridge-android")
val framework = file(System.getenv("MEGALODONTE_ANDROID_HOME") ?: "../../framework/megalodonte-android")
require(framework.resolve("android-bridge/build.gradle.kts").isFile) {
    "Set MEGALODONTE_ANDROID_HOME to a checkout of megalodonte-android"
}
project(":megalodonte-base-android").projectDir = framework.resolve("base-android")
project(":megalodonte-bridge-android").projectDir = framework.resolve("android-bridge")
includeBuild(framework.resolve("../libraries/megalodonte-core"))
includeBuild(framework.resolve("../libraries/megalodonte-theme"))
