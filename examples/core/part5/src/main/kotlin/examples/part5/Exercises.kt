package examples.part5

import kotlin.reflect.KProperty

// Reference solutions for the Part 5 (Functional & Advanced) exercises.

/** Higher-order functions: apply f to x twice. */
fun applyTwice(x: Int, f: (Int) -> Int): Int = f(f(x))

/** Scope functions: format a possibly-null name. */
fun formatUser(name: String?, age: Int): String =
    (name?.let { it } ?: "unknown") + " ($age)"

/** Operators: money with addition and comparison. */
data class Money(val cents: Int) : Comparable<Money> {
    operator fun plus(other: Money) = Money(cents + other.cents)
    override fun compareTo(other: Money) = cents.compareTo(other.cents)
}

/** DSL: collect item names with a lambda-with-receiver builder. */
class MenuBuilder {
    val items = mutableListOf<String>()
    fun item(name: String) { items.add(name) }
}

fun menu(block: MenuBuilder.() -> Unit): List<String> =
    MenuBuilder().apply(block).items

/** Exceptions: division wrapped in a Result. */
fun safeDivide(a: Int, b: Int): Result<Int> = runCatching { a / b }

/** Delegation: a String property that ignores blank assignments. */
class NonBlank(initial: String) {
    private var stored = initial
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = stored
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.isNotBlank()) stored = value
    }
}
