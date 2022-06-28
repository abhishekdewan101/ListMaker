import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.adewan.android.library")
    id("com.squareup.sqldelight")
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

    defaultConfig {
        buildConfigField("String", "AuthenticationUrl", properties.getProperty("authenticationUrl"))
    }
}

sqldelight {
    database("ListMakerDB") {
        packageName = "com.adewan.listmaker.db"
    }
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.sqlDelight.android.driver)
    implementation(libs.sqlDelight.coroutines)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}