---
layout: default
title: "Part 6: Coroutines & Concurrency"
nav_order: 7
has_children: true
permalink: /part6/
---

# Part 6: Coroutines & Concurrency

Write asynchronous, concurrent code with Kotlin coroutines.

## What You'll Learn

- Suspend functions and the coroutine builders `launch`/`async`
- Scopes, context, `Job`, and dispatchers
- Structured concurrency, cancellation, and exception handling
- Asynchronous streams with `Flow`
- Channels for coroutine communication
- Testing coroutine and `Flow` code

## Chapters

| Chapter | Description |
|---------|-------------|
| [Suspend Functions & Builders]({% link part6/01-suspend-builders.md %}) | `suspend`, `launch`, `async`/`await`, `runBlocking` |
| [Scopes, Context & Dispatchers]({% link part6/02-scopes-dispatchers.md %}) | Context, `Job`, dispatchers, `withContext` |
| [Structured Concurrency]({% link part6/03-structured-concurrency.md %}) | Cancellation, timeouts, exception propagation |
| [Asynchronous Streams with Flow]({% link part6/04-flow.md %}) | Cold streams, operators, `StateFlow`/`SharedFlow` |
| [Channels]({% link part6/05-channels.md %}) | Producer/consumer communication |
| [Testing Coroutines]({% link part6/06-testing-coroutines.md %}) | `runTest`, virtual time, test dispatchers |
