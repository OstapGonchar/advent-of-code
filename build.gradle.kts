plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "org.ostap"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    maxHeapSize = "16g"
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}