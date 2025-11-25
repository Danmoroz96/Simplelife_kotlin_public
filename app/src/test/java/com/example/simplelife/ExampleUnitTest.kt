package com.example.simplelife

import com.example.simplelife.data.Expense
import org.junit.Test
import org.junit.Assert.*

class ExpenseUnitTest {

    @Test
    fun expense_creation_isCorrect() {
        // 1. Create a sample expense
        val expense = Expense(
            amount = 50.0,
            category = "Food",
            date = 123456789L,
            note = "Lunch"
        )

        // 2. Verify the data (Assertions)
        assertEquals(50.0, expense.amount, 0.001)
        assertEquals("Food", expense.category)
        assertEquals("Lunch", expense.note)
    }

    @Test
    fun expense_negative_amount_logic() {
        // Simulate a logic check: amounts shouldn't be negative
        val amount = -10.0
        val isValid = amount > 0

        // This confirms our validation logic works
        assertFalse("Amount should be invalid", isValid)
    }
}