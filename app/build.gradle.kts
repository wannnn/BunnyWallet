plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
}

dependencies {
    // Activity
    implementation(libs.activity.compose)

    // Lifecycle
    implementation(libs.lifecycle.ktx)

    // Core KTX
    implementation(libs.core.ktx)

    // Compose UI
    implementation(libs.bundles.androidx.compose.ui)

    // Compose Foundation
    implementation(libs.bundles.androidx.compose.foundation)

    // Material 3
    implementation(libs.compose.material3)


    /** Testing Start */
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)

    // Compose UI
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.compose.ui.test)
    /** Testing End */
}