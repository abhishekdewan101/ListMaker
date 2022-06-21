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
    api(project(":domain"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}