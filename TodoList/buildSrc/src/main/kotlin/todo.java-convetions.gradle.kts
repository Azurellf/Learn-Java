plugins {
    java
    checkstyle
    jacoco
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

    testImplementation(platform(libs.findLibrary("junit").get()))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.findLibrary("mockito").get())
    testImplementation(libs.findLibrary("assertj").get())

    implementation(libs.findLibrary("guava").get())
    implementation(libs.findLibrary("jackson").get())
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.test)
    violationRules {
        rule {
            element = "CLASS"
            excludes = listOf("com.github.azurellf.todo.util.*")
            limit {
                minimum = "1.00".toBigDecimal()
            }
        }
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}