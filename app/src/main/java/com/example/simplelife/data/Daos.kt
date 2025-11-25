package com.example.simplelife.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// --- Expense DAO ---
@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT SUM(amount) FROM expenses")
    fun getTotalSpending(): Flow<Double?>
}

// --- Habit DAO ---
@Dao
interface HabitDao {
    @Query("SELECT * FROM habits WHERE date = :date")
    fun getHabitsByDate(date: Long): Flow<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Query("UPDATE habits SET isCompleted = :completed WHERE id = :id")
    suspend fun updateHabitStatus(id: Int, completed: Boolean)

    @Delete
    suspend fun deleteHabit(habit: Habit)
}

// --- Mood DAO ---
@Dao
interface MoodDao {
    @Query("SELECT * FROM moods ORDER BY date DESC")
    fun getAllMoods(): Flow<List<MoodEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: MoodEntry)
}