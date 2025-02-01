plugins {
    alias(libs.plugins.kotlin.jvm)
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
    api(libs.gson)
    api(libs.kotlinx.collections.immutable)

    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.engine)
}

tasks.test {
    useJUnitPlatform()
}
