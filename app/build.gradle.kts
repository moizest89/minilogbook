plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "com.moizest89.roche.minilogbook"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.moizest89.roche.minilogbook"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    viewBinding = true
  }
}
kapt {
  correctErrorTypes = true
}

dependencies {

  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material.material)
  implementation(libs.constraintlayout.constraintlayout)
  implementation(libs.lifecycle.viewmodel.ktx)

  implementation(libs.android.navigation.fragment)
  implementation(libs.android.navigation.ui)

  implementation(libs.dagger.hilt.android)
  kapt(libs.dagger.hilt.compiler)

  implementation(project(":presentation"))

  testImplementation(libs.junit)
  androidTestImplementation("androidx.test.ext:junit:1.2.1")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

}