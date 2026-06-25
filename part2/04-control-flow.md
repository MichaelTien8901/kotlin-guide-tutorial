---
layout: default
title: "Control Flow"
parent: "Part 2: Language Basics"
nav_order: 4
---

# Control Flow

In Kotlin, `if` and `when` are **expressions** — they return a value.

## `if` as an expression

```kotlin
val max = if (a > b) a else b          // returns a value

// still usable as a statement
if (ready) {
    start()
} else {
    wait()
}
```

There is no ternary `?:` operator for this — `if/else` does the job.

## `when`

`when` is a powerful switch. As an expression it must be exhaustive:

```kotlin
val label = when (x) {
    0 -> "zero"
    1, 2, 3 -> "small"          // multiple values
    in 4..10 -> "medium"        // a range
    else -> "large"
}
```

`when` without a subject acts like a chain of conditions:

```kotlin
val sign = when {
    x > 0 -> "positive"
    x < 0 -> "negative"
    else -> "zero"
}
```

It can also test types (covered with smart casts in
[Part 3]({% link part3/04-data-enum-sealed.md %})):

```kotlin
when (obj) {
    is String -> println("length ${obj.length}")
    is Int -> println("doubled ${obj * 2}")
    else -> println("other")
}
```

## Loops and ranges

```kotlin
for (i in 1..5) print(i)        // 12345  (inclusive)
for (i in 0 until 5) print(i)   // 01234  (exclusive end)
for (i in 10 downTo 1 step 2) print(i)  // 10 8 6 4 2

for (item in listOf("a", "b")) println(item)

for ((index, value) in listOf("a", "b").withIndex()) {
    println("$index: $value")
}
```

`in` also tests membership:

```kotlin
if (x in 1..100) println("in range")
```

## `while` and `do/while`

```kotlin
while (count < 10) { count++ }

do {
    count--
} while (count > 0)
```

## `break`, `continue`, and labels

```kotlin
outer@ for (i in 1..3) {
    for (j in 1..3) {
        if (i + j == 4) continue@outer
        if (i * j > 6) break@outer
    }
}
```

## Exercises

1. Use a `when` expression to map a day number (1–7) to its name.

2. Write `fizzbuzz(n: Int): String` returning `"FizzBuzz"` if `n` is divisible by
   15, `"Fizz"` if by 3, `"Buzz"` if by 5, otherwise the number as a string.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun fizzbuzz(n: Int): String = when {
       n % 15 == 0 -> "FizzBuzz"
       n % 3 == 0 -> "Fizz"
       n % 5 == 0 -> "Buzz"
       else -> n.toString()
   }
   {% endhighlight %}

   </details>

---

Previous: [Null Safety]({% link part2/03-null-safety.md %}) ·
Next: [Functions]({% link part2/05-functions.md %})
