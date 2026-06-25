package examples.part3

// Reference solutions for the Part 3 (OOP & Functions) exercises.
// Compiled and tested by CI so the published answers always work.

/** Classes: a bank account that protects its balance invariant. */
class BankAccount(initial: Long = 0) {
    var balance: Long = initial
        private set

    fun deposit(amount: Long) {
        require(amount > 0) { "amount must be positive" }
        balance += amount
    }

    fun withdraw(amount: Long) {
        require(amount > 0) { "amount must be positive" }
        require(amount <= balance) { "insufficient funds" }
        balance -= amount
    }
}

/** Sealed classes: a closed set of shapes. */
sealed interface Shape {
    data class Circle(val radius: Double) : Shape
    data class Rectangle(val width: Double, val height: Double) : Shape
}

/** Exhaustive `when` over the sealed type — no `else` needed. */
fun area(shape: Shape): Double = when (shape) {
    is Shape.Circle -> Math.PI * shape.radius * shape.radius
    is Shape.Rectangle -> shape.width * shape.height
}

/** Extensions: case- and punctuation-insensitive palindrome check. */
fun String.isPalindrome(): Boolean {
    val clean = lowercase().filter { it.isLetterOrDigit() }
    return clean == clean.reversed()
}
