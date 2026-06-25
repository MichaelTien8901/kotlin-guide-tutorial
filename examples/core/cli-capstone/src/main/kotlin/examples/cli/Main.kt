package examples.cli

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.io.File

/** Analyze several files concurrently, one coroutine per file. */
suspend fun analyzeFiles(paths: List<String>): Map<String, Stats> = coroutineScope {
    paths.map { path ->
        async(Dispatchers.IO) {
            val text = runCatching { File(path).readText() }.getOrElse { "" }
            path to textStats(text)
        }
    }.awaitAll().toMap()
}

fun printStats(name: String, stats: Stats) {
    println("== $name ==")
    println("lines: ${stats.lines}, words: ${stats.words}, chars: ${stats.chars}")
    println("top words:")
    stats.topWords.forEach { (word, count) -> println("  %4d  %s".format(count, word)) }
}

/**
 * Usage:
 *   textstats <file>...     analyze each file (concurrently)
 *   textstats               read from stdin
 */
fun main(args: Array<String>) = runBlocking {
    if (args.isEmpty()) {
        val text = generateSequence(::readLine).joinToString("\n")
        printStats("stdin", textStats(text))
    } else {
        analyzeFiles(args.toList()).forEach { (path, stats) -> printStats(path, stats) }
    }
}
