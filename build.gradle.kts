plugins {
    kotlin("multiplatform") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
}

group = "org.kooparts"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "org.kooparts.main"
            }
        }
    }
    sourceSets {
        val ktorVersion = "2.2.1"
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-curl:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("net.mamoe.yamlkt:yamlkt:0.12.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test")) // This brings all the platform dependencies automatically
                implementation("io.ktor:ktor-client-mock:$ktorVersion")
            }
        }
        val nativeMain by getting
        val nativeTest by getting
    }
}
