plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.ui.common"
}

dependencies {
    implementation(libs.accompanist.systemUiController)
    implementation(libs.compose.material3)
}