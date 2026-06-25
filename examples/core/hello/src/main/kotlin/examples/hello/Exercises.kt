package examples.hello

// Reference solutions for the Part 1 "Hello, World!" exercises.
// These are compiled and tested by CI so the published answers always work.

/** Exercise 1: uppercase the message and append an exclamation mark. */
fun shout(message: String): String = "${message.uppercase()}!"

/** Exercise 2: greet several names, one per line. */
fun greetAll(vararg names: String): String =
    names.joinToString(separator = "\n") { greeting(it) }
