plugins {
    id("com.adewan.android.application")
    id("com.adewan.hilt.plugin")
}

dependencies {
    implementation(project(":navigation"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.material3)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}