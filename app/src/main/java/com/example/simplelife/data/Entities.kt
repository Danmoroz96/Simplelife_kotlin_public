package com.example.simplelife.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// 1. Expense Table
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val category: String, // e.g., "Food", "Transport"
    val date: Long,       // Stored as timestamp
    val note: String
)

// 2. Habit Table
@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,         // e.g., "Drink Water"
    val isCompleted: Boolean, // True/False
    val date: Long
)

// 3. Mood Table
@Entity(tableName = "moods")
data class MoodEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val moodRating: Int, // 1=Sad, 5=Happy
    val reflection: String,
    val date: Long
)