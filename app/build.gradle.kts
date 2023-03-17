import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.dagger.hilt.android")

    kotlin("kapt")
}

// Load keystore properties
val keystorePropsFile = rootProject.file("keystore.properties")
val keystoreProps = Properties().apply {
    load(FileInputStream(keystorePropsFile))
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
            buildConfigField(type = "String", name = "ETH_DOMAIN", value = "\"eth-mainnet\"")
            buildConfigField(type = "String", name = "ETH_KEY", value = "\"${keystoreProps["prodEthKey"]}\"")
        }
        getByName("debug") {
            buildConfigField(type = "String", name = "ETH_DOMAIN", value = "\"eth-goerli\"")
            buildConfigField(type = "String", name = "ETH_KEY", value = "\"${keystoreProps["stgEthKey"]}\"")
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

    // Coroutines
    implementation(libs.coroutines.android)

    // Web3j
    implementation(libs.web3j.android)

    // DataStore
    implementation(libs.datastore.preferences)

    // Timber
    implementation(libs.timber)

    /** Testing Start */
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)

    // Compose UI
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.compose.ui.test)

    // Coroutines
    testImplementation(libs.coroutines.test)
    /** Testing End */
}