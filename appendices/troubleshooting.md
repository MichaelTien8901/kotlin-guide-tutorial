---
layout: default
title: "Troubleshooting"
parent: "Appendices"
nav_order: 4
---

# Troubleshooting

Common issues and fixes when working through this guide.

## Gradle & build

**"Cannot resolve external dependency ... because no repositories are defined"**
: A Gradle build needs `mavenCentral()` (and `google()` for Android) declared in
`settings.gradle.kts` under `dependencyResolutionManagement { repositories { } }`.

**"Unsupported class file major version" / Java version errors**
: Use **JDK 17**. Check `java -version`, and set the Gradle JDK in your IDE's
settings.

**Gradle wrapper fails to download the distribution**
: Check network/proxy. The version is pinned in
`gradle/wrapper/gradle-wrapper.properties`.

**Kotlin/Compose compiler version mismatch**
: With Kotlin 2.0+, apply the `org.jetbrains.kotlin.plugin.compose` plugin (it
matches the Kotlin version). Do not set a manual `kotlinCompilerExtensionVersion`.

## Android Studio

**Gradle sync fails after creating a project**
: Update Android Studio and the AGP version, then **File → Invalidate Caches /
Restart**.

**Emulator won't start**
: Enable hardware acceleration (VT-x/AMD-V) in BIOS; create a fresh AVD in Device
Manager.

**`@Composable` function call errors**
: Composables can only be called from other composables or from `setContent`.

## Coroutines

**`Module with the Main dispatcher is missing`** in unit tests
: Call `Dispatchers.setMain(StandardTestDispatcher())` before and
`Dispatchers.resetMain()` after, and use `runTest`. Add
`kotlinx-coroutines-test`.

**A coroutine never finishes / test hangs**
: You may be blocking instead of suspending, or not advancing virtual time. Use
`advanceUntilIdle()` inside `runTest`.

## GitHub Pages

**Site builds locally but links break on Pages**
: Set `baseurl` in `_config.yml` to your repo name, and use `{% raw %}{% link path %}{% endraw %}`
for internal links rather than hard-coded URLs.

**Pages shows a 404 after enabling**
: Pages can take a minute on first deploy. Confirm the Pages source is set to
**GitHub Actions** and the workflow succeeded.

## Jekyll (local preview)

**`bundle: command not found`**
: Install Ruby and Bundler, then run `bundle install` in `docs/`.

**A page renders code literally / Liquid errors**
: If a code sample contains Liquid-like syntax (double curly braces, or `{%`-style
tags), Jekyll tries to interpret it. Wrap that sample in a Liquid `raw` /
`endraw` block so it is rendered verbatim.
