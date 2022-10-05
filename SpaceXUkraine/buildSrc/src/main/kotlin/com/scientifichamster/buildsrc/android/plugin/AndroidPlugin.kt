package com.scientifichamster.buildsrc.android.plugin
//
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.kotlin.dsl.*
//import com.scientifichamster.buildsrc.Dependencies
//import com.scientifichamster.buildsrc.TestDependencies
//import com.android.build.gradle.*
//
//class AndroidPlugin : Plugin<Project> {
//
//    override fun apply(project: Project) {
//        @Suppress("UnstableApiUsage")
//        val extension = project.extensions.create<AndroidPluginExtension>("androidPlugin")
//
//    }
//
//    private fun androidPlugins() = listOf(
//        "kotlin-android",
//        "kotlin-android-extensions"
//    )
//
//    private fun Project.configurePlugins(buildType: BuildType) = listOf(
//        when (buildType) {
//            BuildType.App, BuildType.AndroidLibrary -> androidPlugins()
//            BuildType.Library -> listOf("kotlin")
//        },
//        listOf("kotlin-kapt")
//    ).flatten()
//        .also { println("AndroidPlugin: applying plugins $it") }
//        .forEach(plugins::apply)
//
//    private fun Project.configureAndroid() = extensions.getByType(BaseExtention::class.java)
//
//    private fun Project.configureDependencies() = dependencies {
//        fun kapt(definition: Any) = "kapt"(definition)
//        fun implementation(definition: Any) = "implementation"(definition)
//        fun testImplementation(definition: Any) = "testImplementation"(definition)
//        fun androidTestImplementation(definition: Any) = "androidTestImplementation"(definition)
//
//        implementation(kotlin("stdlib-jdk7"))
//        testImplementation(kotlin("test"))
//
//        implementation(Dependencies.kotlinxCoroutines)
//
//        implementation(Dependencies.timber)
//
//        kapt(Dependencies.Dagger.daggerCompiler)
//        kapt(Dependencies.Dagger.daggerAndroidProcessor)
//
//        androidTestImplementation(TestDependencies.AndroidX.core)
//        androidTestImplementation(TestDependencies.AndroidX.coreKtx)
//        androidTestImplementation(TestDependencies.AndroidX.runner)
//        androidTestImplementation(TestDependencies.AndroidX.rules)
//        androidTestImplementation(TestDependencies.AndroidX.espressoCore)
//        androidTestImplementation(TestDependencies.AndroidX.espressoContrib)
//        androidTestImplementation(TestDependencies.AndroidX.junit)
//        testImplementation(TestDependencies.livedataTesting)
//
//        testImplementation(TestDependencies.kotlinxCoroutines)
//        androidTestImplementation(TestDependencies.kotlinxCoroutines)
//
//        testImplementation(TestDependencies.JUnit.junit)
//        testImplementation(TestDependencies.JUnit.junitPlatformRunner)
//
//        testImplementation(TestDependencies.Mockito.mockitoCore)
//        testImplementation(TestDependencies.Mockito.mockitoInline)
//        testImplementation(TestDependencies.Mockito.mockitoKotlin)
//        testImplementation(TestDependencies.AndroidX.coreTesting)
//    }
//}
//
//class AndroidPluginExtension {
//    var buildType = BuildType.AndroidLibrary
//}
//
//enum class BuildType {
//    Library,
//    AndroidLibrary,
//    App
//}