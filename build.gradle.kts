plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.cheonjaeung.gson"
version = "1.0.0"

kotlin {
    jvmToolchain(8)
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.google.code.gson:gson:2.11.0")
    api("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.8")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
