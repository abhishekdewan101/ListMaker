plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.common.navigation"
}

dependencies {
    implementation(libs.compose.navigation)
}