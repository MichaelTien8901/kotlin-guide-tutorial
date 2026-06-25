dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "kotlin-guide-examples-core"

// One subproject per example/part. Add new examples here as the guide grows.
include("hello")
include("part2")
include("part3")
