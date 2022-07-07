import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.adewan.android.library")
    id("com.adewan.hilt.plugin")
    id("kotlinx-serialization")
}

android {
    namespace = "com.adewan.listmaker.data"

    val properties = Properties()
    try {
        println("Found local.properties so reading from that")
        properties.load(project.rootProject.file("local.properties").inputStream())
    } catch (e: Exception) {
        println("No local.properties found so authentication won't work")
        properties.setProperty("clientId", "")
        properties.setProperty("useTwitchAuthentication", "false")
        properties.setProperty("clientAuthenticationUrl", "")
    }

    kapt { arguments { arg("room.schemaLocation", "$projectDir/schemas") } }

    defaultConfig {
        buildConfigField("String", "AuthenticationUrl", properties.getProperty("authenticationUrl"))
        buildConfigField("String", "ClientId", properties.getProperty("clientId"))
        buildConfigField("String", "TMDBToken", properties.getProperty("tmdbToken"))
    }
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.core)
    implementation(libs.ktor.client)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.content.negotiation)

    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
}
