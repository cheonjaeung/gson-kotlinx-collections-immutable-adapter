import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
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

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates("${project.group}", "kotlinx-collections-immutable-adapter", "${project.version}")

    pom {
        name.set("Gson Kotlin Immutable Collections Type Adapter")
        description.set("A Gson type adapter library to deserialize Kotlin immutable collections.")
        url.set("https://github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter")

        licenses {
            license {
                name.set("Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("cheonjaeung")
                name.set("Cheon Jaeung")
                email.set("cheonjaewoong@gmail.com")
            }
        }

        scm {
            url.set("https://github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter")
            connection.set("scm:git:git://github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter.git")
            developerConnection.set("scm:git:ssh://git@github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter.git")
        }
    }
}
