object Build {
    private const val gradleBuildTools = "7.1.1"
    private const val jacocoVersion = "0.8.7"
    const val buildTools = "com.android.tools.build:gradle:$gradleBuildTools"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
    const val ktlintGradlePlugin = "org.jlleitschuh.gradle:ktlint-gradle:10.1.0"
    const val jacocoPlugin = "org.jacoco:org.jacoco.core:$jacocoVersion"

}
