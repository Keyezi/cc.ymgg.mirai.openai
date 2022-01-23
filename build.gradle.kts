plugins {
    val kotlinVersion = "1.5.30"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.9.2"
}

group = "cc.ymgg"
version = "2021.11"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
    //jcenter()
}
dependencies {
    
    implementation("io.fusionauth:fusionauth-jwt:5.0.0")
    implementation("com.alibaba:fastjson:1.2.78")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("com.google.zxing:core:3.4.1")
    implementation("com.google.zxing:javase:3.4.1")
//    implementation ("io.ktor:ktor-server-core:1.5.2")
//    implementation ("io.ktor:ktor-server-netty:1.5.2")
//    implementation ("io.ktor:ktor-serialization:1.5.2")
    
}
