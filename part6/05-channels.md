---
layout: default
title: "Channels"
parent: "Part 6: Coroutines & Concurrency"
nav_order: 5
---

# Channels

A `Channel` is a coroutine-safe queue for passing values between coroutines —
think of it as a pipe with a sender and a receiver. Where a `Flow` is cold and
declarative, a `Channel` is hot and imperative.

## Sending and receiving

```kotlin
import kotlinx.coroutines.channels.Channel

val channel = Channel<Int>()

launch {                       // producer
    for (i in 1..3) channel.send(i)
    channel.close()
}

launch {                       // consumer
    for (value in channel) {   // iterates until the channel is closed
        println(value)
    }
}
```

`send` suspends if the channel is full; `receive` suspends if it is empty.

## The `produce` builder

`produce` creates a coroutine and a channel together, returning a
`ReceiveChannel`:

```kotlin
fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (i in 1..5) send(i * i)
}

val squares = produceSquares()
for (sq in squares) println(sq)
```

## Buffering

By default a channel is **rendezvous** (capacity 0): each `send` waits for a
`receive`. Other capacities change the behavior:

```kotlin
Channel<Int>(capacity = 10)             // buffered
Channel<Int>(Channel.UNLIMITED)         // never suspends on send
Channel<Int>(Channel.CONFLATED)         // keeps only the latest value
```

## Channel vs Flow — which?

| Use a `Flow` | Use a `Channel` |
|--------------|-----------------|
| A stream a collector consumes | Hand-off between specific coroutines |
| Cold, restarts per collector | Hot, values consumed once |
| Declarative pipelines | Producer/consumer coordination |

For most app code, prefer `Flow`. Reach for channels when you need direct
coroutine-to-coroutine communication (e.g. a work queue).

## Exercises

1. Use `produce` to generate the first five Fibonacci numbers and collect them
   into a list.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun CoroutineScope.fibonacci(count: Int): ReceiveChannel<Int> = produce {
       var a = 0; var b = 1
       repeat(count) {
           send(a)
           val next = a + b; a = b; b = next
       }
   }
   {% endhighlight %}

   </details>

2. Create a `CONFLATED` channel and show that a slow consumer only sees the
   latest value.

This solution is in `examples/core/part6/` and is tested by CI.

---

Previous: [Asynchronous Streams with Flow]({% link part6/04-flow.md %}) ·
Next: [Testing Coroutines]({% link part6/06-testing-coroutines.md %})
