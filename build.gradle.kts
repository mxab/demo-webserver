plugins {
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("io.micronaut.application") version "2.0.8"
}

version = "0.1"
group = "com.github.mxab"

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.github.mxab.*")
    }
}

dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.views:micronaut-views-thymeleaf")
    implementation("javax.annotation:javax.annotation-api")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

}


application {
    mainClass.set("com.github.mxab.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


dockerBuild {
    images = ["${System.env.DOCKER_IMAGE ?: project.name}:$project.version"]
}

dockerBuildNative {
    images = ["${System.env.DOCKER_IMAGE ?: project.name}:$project.version"]
}

