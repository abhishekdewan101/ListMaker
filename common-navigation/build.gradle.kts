plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.common.navigation"
}

dependencies {
    api(libs.androidx.hilt.navigation.compose)
    api(libs.androidx.navigation.compose)
}