package examples.hello

/** Returns a friendly greeting. Pure function so it is easy to unit-test. */
fun greeting(name: String): String = "Hello, $name!"

fun main() {
    println(greeting("Kotlin"))
}
