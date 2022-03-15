object Tests {
    // Test

    // Dependencies for local unit tests
    private const val junitVersion = "4.13.2"
    private const val extJunitVersion = "1.1.3"
    private const val mockkVersion = "1.12.0"

    private const val espressoCoreVersion = "3.5.0-alpha04"
    private const val robolectricVersion = "4.7.3"

    // AndroidX Test - Instrumented testing
    private const val archCoreTesting = "2.1.0"

    const val junit = "junit:junit:$junitVersion"
    const val extJUnit = "androidx.test.ext:junit:$extJunitVersion"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
    const val coreTesting = "androidx.arch.core:core-testing:$archCoreTesting"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val coroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlin.coroutinesVersion}"
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"
    const val core = "androidx.test:core:1.4.0"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
    const val composeDebugTest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Hilt.version}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Hilt.version}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.6.0"
    const val idling = "com.jakewharton.espresso:okhttp3-idling-resource:1.0.0"
}
