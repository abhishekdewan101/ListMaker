import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.adewan.android.application")
    id("com.adewan.hilt.plugin")
}

dependencies {
    implementation(project(":navigation"))

    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}