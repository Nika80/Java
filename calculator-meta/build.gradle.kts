plugins {
    id("java")
}

group = "ru.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(platform("org.testcontainers:testcontainers-bom:1.20.4"))
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}