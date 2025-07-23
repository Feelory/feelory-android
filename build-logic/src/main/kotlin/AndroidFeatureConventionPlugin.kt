import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import com.feelory.build_logic.libs

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "feelory.android.library")
//            apply(plugin = "mscs.hilt")

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
//                "implementation"(project(":core:navigation"))
//                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
//                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
//                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("androidx.compose.navigation").get())
            }
        }
    }
}
