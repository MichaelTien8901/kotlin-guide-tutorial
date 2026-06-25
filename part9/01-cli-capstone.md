---
layout: default
title: "CLI Capstone: Text Stats"
parent: "Part 9: Real-World Projects"
nav_order: 1
---

# CLI Capstone: Text Stats

Build a command-line tool that reports line, word, and character counts plus the
most frequent words — for stdin or for several files analyzed **concurrently**.
It pulls together functions, collections, error handling, and coroutines from
Parts 1–6.

The complete project is in `examples/core/cli-capstone/`.

## The core logic

A pure function does the analysis, which makes it trivial to test:

```kotlin
data class Stats(
    val lines: Int,
    val words: Int,
    val chars: Int,
    val topWords: List<Pair<String, Int>>,
)

fun textStats(text: String, topN: Int = 5): Stats {
    val lineList = if (text.isEmpty()) emptyList() else text.trimEnd('\n').split("\n")
    val words = text.split(Regex("\\s+")).filter { it.isNotBlank() }
    val frequencies = words
        .map { it.lowercase().trim('.', ',', '!', '?', ';', ':', '"', '\'') }
        .filter { it.isNotEmpty() }
        .groupingBy { it }
        .eachCount()
    val top = frequencies.entries
        .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
        .take(topN)
        .map { it.key to it.value }
    return Stats(lineList.size, words.size, text.length, top)
}
```

This uses **collection operations** (`map`, `filter`, `groupingBy`, `sortedWith`)
and is a pure function — exactly the testable core the guide has emphasized.

## Concurrency for multiple files

When given several files, analyze them in parallel with `async`:

```kotlin
suspend fun analyzeFiles(paths: List<String>): Map<String, Stats> = coroutineScope {
    paths.map { path ->
        async(Dispatchers.IO) {
            val text = runCatching { File(path).readText() }.getOrElse { "" }
            path to textStats(text)
        }
    }.awaitAll().toMap()
}
```

Note the **error handling** with `runCatching` so one unreadable file does not
crash the whole run.

## The entry point

```kotlin
fun main(args: Array<String>) = runBlocking {
    if (args.isEmpty()) {
        val text = generateSequence(::readLine).joinToString("\n")
        printStats("stdin", textStats(text))
    } else {
        analyzeFiles(args.toList()).forEach { (path, stats) -> printStats(path, stats) }
    }
}
```

## Running it

```bash
cd examples/core
echo "the cat sat the cat" | ./gradlew --quiet :cli-capstone:run
# or analyze files
./gradlew :cli-capstone:run --args="README.md build.gradle.kts"
```

The logic is covered by `StatsTest`, which runs in CI.

## Extension challenges

1. Add a `--top N` flag to control how many words are shown.

   <details>
   <summary>Solution sketch</summary>

   {% highlight kotlin %}
   val topN = args.indexOf("--top").takeIf { it >= 0 }
       ?.let { args.getOrNull(it + 1)?.toIntOrNull() } ?: 5
   val files = args.filterNot { it == "--top" || it.toIntOrNull() != null }
   {% endhighlight %}

   </details>

2. Add a total summary line aggregating stats across all files.

3. Exclude common stop-words ("the", "a", "and") from the top list.

---

Next: [Android Capstone]({% link part9/02-android-capstone.md %})
