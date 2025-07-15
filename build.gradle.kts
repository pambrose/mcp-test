val kotlin_version: String by project
val logback_version: String by project

plugins {
  kotlin("jvm") version "2.1.10"
  id("io.ktor.plugin") version "3.2.1"
}

group = "com.example"
version = "0.0.1"

application {
  mainClass = "io.ktor.server.cio.EngineMain"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.ktor:ktor-server-core")
  implementation("io.ktor:ktor-server-cio")
  implementation("io.modelcontextprotocol:kotlin-sdk:0.5.0")
  implementation("io.ktor:ktor-server-config-yaml")
  implementation("ch.qos.logback:logback-classic:${logback_version}")
  testImplementation("io.ktor:ktor-server-test-host")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
