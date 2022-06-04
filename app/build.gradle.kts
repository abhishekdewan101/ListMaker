plugins {
    id("com.adewan.android.application")
}

dependencies {
    implementation(project(":navigation"))

    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
}