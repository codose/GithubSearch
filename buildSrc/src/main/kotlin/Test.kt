object Test {
    // Test

    // Dependencies for local unit tests
    private const val junitVersion = "4.13.2"
    private const val extJunitVersion = "1.1.3"
    private const val mockkVersion = "1.12.0"

    private const val espressoCoreVersion = "3.4.0"
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
}
