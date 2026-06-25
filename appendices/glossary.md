---
layout: default
title: "Glossary"
parent: "Appendices"
nav_order: 1
---

# Glossary

Common terms used throughout this guide.

## A

**AGP (Android Gradle Plugin)**
: The Gradle plugin that builds Android apps; its version must be compatible with
the Gradle and Kotlin versions.

**Activity**
: An Android component representing a single screen with a UI.

## C

**Composable**
: A `@Composable` function that describes UI in Jetpack Compose.

**Coroutine**
: A lightweight, suspendable computation for asynchronous code without blocking
threads.

**Companion object**
: An object declared inside a class for members tied to the class itself (like
`static` in Java).

## D

**Data class**
: A class whose `equals`/`hashCode`/`toString`/`copy` are generated from its
properties.

**Dispatcher**
: Decides which thread(s) a coroutine runs on (`Default`, `IO`, `Main`).

**DSL**
: Domain-specific language; in Kotlin, built with lambdas-with-receiver.

## E

**Extension function**
: A function added to a type from outside its definition.

## F

**Flow**
: A cold asynchronous stream of values, like a suspending sequence.

## N

**Null safety**
: Kotlin's type-system feature distinguishing nullable (`T?`) from non-null (`T`)
types.

## S

**Sealed class/interface**
: A type with a fixed, compiler-known set of subtypes, enabling exhaustive
`when`.

**StateFlow**
: A hot flow that always holds the latest value; the standard way to expose UI
state.

**Suspend function**
: A function that can pause and resume without blocking its thread.

## V

**Variance**
: How subtyping of a generic relates to its type argument (`out` = covariant,
`in` = contravariant).

**ViewModel**
: A lifecycle-aware holder of UI state that survives configuration changes.

## W

**WorkManager**
: An Android library for deferrable, guaranteed background work.
