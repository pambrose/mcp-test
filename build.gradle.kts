plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ktor)
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
  implementation(libs.ktor.server.core)
  implementation(libs.ktor.server.cio)
  implementation(libs.mcp.kotlin.sdk)
  implementation(libs.ktor.server.config.yaml)
  implementation(libs.logback.classic)
  testImplementation(libs.ktor.server.test.host)
  testImplementation(libs.kotlin.test.junit)
}
