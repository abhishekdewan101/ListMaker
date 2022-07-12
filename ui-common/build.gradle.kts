plugins { id("com.adewan.compose.library") }

android { namespace = "com.adewan.listmaker.ui.common" }

dependencies {
    api(project(":common-navigation"))
    api(project(":core"))
    implementation(libs.accompanist.systemUiController)
    implementation(libs.material3)
    implementation(libs.androidx.compose.foundation)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}
