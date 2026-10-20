plugins { id("com.android.application") }
android {
    namespace = "megalodonte.app.lista_de_compras"
    compileSdk = 35
    defaultConfig {
        applicationId = "megalodonte.app.lista_de_compras"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
dependencies {
    implementation(project(":framework-core"))
    implementation(project(":android-bridge"))
    implementation("megalodonte:megalodonte-theme:1.0.0-beta")
}
val verifyAndroidDependencies = tasks.register("verifyAndroidDependencies") {
    doLast {
        configurations.getByName("debugRuntimeClasspath").incoming.resolutionResult.allComponents.forEach { component ->
            val module = component.moduleVersion ?: return@forEach
            check(module.group != "org.openjfx") { "JavaFX must not be packaged in Android: $module" }
        }
    }
}
tasks.named("check") { dependsOn(verifyAndroidDependencies) }
