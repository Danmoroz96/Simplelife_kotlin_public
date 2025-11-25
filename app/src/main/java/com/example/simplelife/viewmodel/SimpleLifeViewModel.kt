package com.example.simplelife.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.simplelife.SimpleLifeApplication
import com.example.simplelife.data.Expense
import com.example.simplelife.data.Habit
import com.example.simplelife.data.MoodEntry // Make sure this is imported
import com.example.simplelife.data.SimpleLifeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class SimpleLifeViewModel(private val repository: SimpleLifeRepository) : ViewModel() {

    // --- DATA ---
    val expenses: StateFlow<List<Expense>> = repository.allExpenses
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalSpending: StateFlow<Double?> = repository.totalSpending
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    private val todayStart = getStartOfDay()
    val todayHabits: StateFlow<List<Habit>> = repository.getHabitsForDate(todayStart)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- ACTIONS ---
    fun addExpense(amount: Double, category: String, note: String) {
        viewModelScope.launch {
            repository.addExpense(Expense(0, amount, category, System.currentTimeMillis(), note))
        }
    }

    fun addHabit(name: String) {
        viewModelScope.launch {
            repository.addHabit(Habit(0, name, false, todayStart))
        }
    }

    // --- NEW: ADD MOOD FUNCTION ---
    fun addMood(rating: Int, reflection: String) {
        viewModelScope.launch {
            repository.addMood(MoodEntry(0, rating, reflection, System.currentTimeMillis()))
        }
    }

    fun toggleHabit(habit: Habit) {
        viewModelScope.launch { repository.updateHabitStatus(habit.id, !habit.isCompleted) }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch { repository.deleteHabit(habit) }
    }

    private fun getStartOfDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as SimpleLifeApplication)
                SimpleLifeViewModel(app.repository)
            }
        }
    }
}