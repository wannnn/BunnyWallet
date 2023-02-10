plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.dagger.hilt.android")

    kotlin("kapt")
}

android {
    namespace = "com.cj.bunnywallet"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId  = "com.cj.bunnywallet"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeComplier.get()
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    detekt {
        toolVersion = libs.versions.detekt.get()
        config = files("config/detekt/detekt.yml")
        buildUponDefaultConfig = true
    }
}

dependencies {
    // Activity
    implementation(libs.activity.compose)

    // Lifecycle
    implementation(libs.lifecycle.ktx)

    // Navigation
    implementation(libs.navigation.compose)

    // Core KTX
    implementation(libs.core.ktx)

    // Compose UI
    implementation(libs.bundles.androidx.compose.ui)

    // Compose Foundation
    implementation(libs.bundles.androidx.compose.foundation)

    // Material 3
    implementation(libs.compose.material3)

    // Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)

    /** Testing Start */
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)

    // Compose UI
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.compose.ui.test)
    /** Testing End */
}