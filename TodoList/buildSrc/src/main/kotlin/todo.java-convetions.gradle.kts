plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

dependencies {

    compileOnly(libs.findLibrary("lombok").get())
    annotationProcessor(libs.findLibrary("lombok").get())

    testCompileOnly(libs.findLibrary("lombok").get())
    testAnnotationProcessor(libs.findLibrary("lombok").get())

    testImplementation(platform(libs.findLibrary("junit").get()))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.findLibrary("mockito").get())
    testImplementation(libs.findLibrary("assertj").get())
}

tasks.test {
    useJUnitPlatform()
}