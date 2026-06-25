package examples.part3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.PI

class ExercisesTest {
    @Test
    fun `bank account deposits and withdraws`() {
        val acct = BankAccount(100)
        acct.deposit(50)
        assertEquals(150L, acct.balance)
        acct.withdraw(70)
        assertEquals(80L, acct.balance)
    }

    @Test
    fun `bank account rejects overdraw`() {
        val acct = BankAccount(10)
        assertThrows(IllegalArgumentException::class.java) { acct.withdraw(20) }
    }

    @Test
    fun `area handles circle and rectangle`() {
        assertEquals(PI * 4, area(Shape.Circle(2.0)), 1e-9)
        assertEquals(6.0, area(Shape.Rectangle(2.0, 3.0)), 1e-9)
    }

    @Test
    fun `isPalindrome ignores case and punctuation`() {
        assertTrue("A man, a plan, a canal: Panama".isPalindrome())
        assertFalse("hello".isPalindrome())
    }
}
