plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias((libs.plugins.kotlinSerialization))
    alias(libs.plugins.compose.compiler)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.converter)
    implementation (libs.retrofit.adapters.result)

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.annotation)
}
