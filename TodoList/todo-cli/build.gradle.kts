plugins {
    id("todo.java-convetions")
    application
}

application {
    mainModule = "todo.cli"
    mainClass = "org.gradle.sample.Main"
}

dependencies {
    implementation(project(":todo-core"))
    implementation(libs.picocli);
}