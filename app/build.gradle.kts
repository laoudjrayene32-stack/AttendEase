plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")   // 👈 هذا الصح
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}
android {
    namespace = "com.attendease.app"
    compileSdk = 34

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }



    defaultConfig {
        applicationId = "com.attendease.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    // 🔥 Compose BOM
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)

    // ✅ Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // ✅ Material3
    implementation("androidx.compose.material3:material3")

    // ✅ Icons
    implementation("androidx.compose.material:material-icons-extended")

    // ✅ Activity
    implementation("androidx.activity:activity-compose:1.8.2")

    // ✅ Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // 🔥 Hilt (مرة واحدة فقط)
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-compiler:2.51")

    // 🔥 Hilt + Navigation Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // 🔥 Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("androidx.compose.material:material-icons-extended")
    // 🔥 Firebase
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // 🔥 Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // ✅ Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // 🔥 Material (باش الثيم يخدم)
    implementation("com.google.android.material:material:1.11.0")
}

// 🔥 kapt
kapt {
    correctErrorTypes = true
}