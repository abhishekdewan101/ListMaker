plugins {
    id("com.adewan.android.library")
    id("com.squareup.sqldelight")
    id("com.adewan.hilt.plugin")
}

android {
    namespace = "com.adewan.listmaker.data"
}

sqldelight {
    database("ListMakerDB") {
        packageName = "com.adewan.listmaker.db"
    }
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.sqlDelight.driver)
    implementation(libs.sqlDelight.coroutines)

    implementation(libs.kotlinx.coroutines)
}