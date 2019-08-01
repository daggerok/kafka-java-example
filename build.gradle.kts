plugins {
    id("com.github.ben-manes.versions").version("0.21.0")
    id("org.jetbrains.kotlin.jvm").version("1.3.41")
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    // kafka
    val kafkaVersion: String by lazy { "2.3.0" }
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
}

application {
    mainClassName = System.getProperty("mainClass", "com.github.daggerok.AppKt")
}

defaultTasks("build", "installDist")

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}

tasks {
    "wrapper"(Wrapper::class) {
        gradleVersion = "5.6-rc-1"
    }
}

// gradle tests output stdOut workaround
tasks {
    test {
        testLogging {
            showExceptions = true
            showStandardStreams = true
            useJUnitPlatform()
            useJUnit()
        }
    }
}
