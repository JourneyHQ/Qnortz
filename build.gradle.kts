plugins {
    kotlin("jvm") version "1.8.0"
}

group = "dev.yuua"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://m2.dv8tion.net/releases")
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))

    implementation("dev.kord:kord-core:0.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta5")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}