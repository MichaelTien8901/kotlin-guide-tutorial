package examples.part2

// Reference solutions for the Part 2 (Language Basics) exercises.
// Compiled and tested by CI so the published answers always work.

/** Variables & Types: area of a circle. */
fun circleArea(radius: Double): Double = Math.PI * radius * radius

/** Strings: uppercased first letter of each space-separated word. */
fun initials(fullName: String): String =
    fullName.split(" ")
        .filter { it.isNotEmpty() }
        .map { it.first().uppercaseChar() }
        .joinToString("")

/** Null Safety: length of the string, or 0 when null. */
fun lengthOrZero(s: String?): Int = s?.length ?: 0

/** Control Flow: classic FizzBuzz for a single number. */
fun fizzbuzz(n: Int): String = when {
    n % 15 == 0 -> "FizzBuzz"
    n % 3 == 0 -> "Fizz"
    n % 5 == 0 -> "Buzz"
    else -> n.toString()
}

/** Functions: factorial with factorial(0) == 1. */
fun factorial(n: Int): Long {
    require(n >= 0) { "n must be non-negative" }
    var result = 1L
    for (i in 2..n) result *= i
    return result
}
