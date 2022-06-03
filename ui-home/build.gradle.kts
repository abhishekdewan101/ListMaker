plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.ui.home"
}

dependencies {
    implementation(libs.compose.material3)
}