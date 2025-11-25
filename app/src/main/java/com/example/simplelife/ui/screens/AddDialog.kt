package com.example.simplelife.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // <--- THIS FIXES YOUR RED ERROR
import androidx.compose.ui.window.Dialog

@Composable
fun AddEntryDialog(
    onDismiss: () -> Unit,
    onAddExpense: (Double, String, String) -> Unit,
    onAddHabit: (String) -> Unit,
    onAddMood: (Int, String) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0=Expense, 1=Habit, 2=Mood

    // Expense State
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    // Habit State
    var habitName by remember { mutableStateOf("") }

    // Mood State
    var moodRating by remember { mutableIntStateOf(3) }
    var moodReflection by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Add New", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                // --- TABS ---
                Row(
                    modifier = Modifier.fillMaxWidth().background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TabButton("Expense", selectedTab == 0) { selectedTab = 0 }
                    TabButton("Habit", selectedTab == 1) { selectedTab = 1 }
                    TabButton("Mood", selectedTab == 2) { selectedTab = 2 }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- CONTENT ---
                when (selectedTab) {
                    0 -> { // EXPENSE
                        OutlinedTextField(
                            value = amount, onValueChange = { amount = it },
                            label = { Text("Amount ($)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = category, onValueChange = { category = it },
                            label = { Text("Category") }, modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = note, onValueChange = { note = it },
                            label = { Text("Note") }, modifier = Modifier.fillMaxWidth()
                        )
                    }
                    1 -> { // HABIT
                        OutlinedTextField(
                            value = habitName, onValueChange = { habitName = it },
                            label = { Text("Habit Name") },
                            placeholder = { Text("e.g., Drink Water") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    2 -> { // MOOD
                        Text("How do you feel?", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            MoodIcon(1, moodRating) { moodRating = 1 }
                            MoodIcon(2, moodRating) { moodRating = 2 }
                            MoodIcon(3, moodRating) { moodRating = 3 }
                            MoodIcon(4, moodRating) { moodRating = 4 }
                            MoodIcon(5, moodRating) { moodRating = 5 }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = moodReflection, onValueChange = { moodReflection = it },
                            label = { Text("Quick Reflection") },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 3
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // --- SAVE BUTTON ---
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (selectedTab == 0 && amount.isNotEmpty()) {
                                onAddExpense(amount.toDoubleOrNull() ?: 0.0, category.ifBlank { "General" }, note)
                                onDismiss()
                            } else if (selectedTab == 1 && habitName.isNotEmpty()) {
                                onAddHabit(habitName)
                                onDismiss()
                            } else if (selectedTab == 2) {
                                onAddMood(moodRating, moodReflection)
                                onDismiss()
                            }
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Composable
fun MoodIcon(rating: Int, currentRating: Int, onClick: () -> Unit) {
    val isSelected = rating == currentRating
    val color = if (isSelected) Color(0xFF2E7D32) else Color.Gray
    val icon = when(rating) {
        1 -> Icons.Default.SentimentVeryDissatisfied
        2 -> Icons.Default.SentimentDissatisfied
        3 -> Icons.Default.SentimentNeutral
        4 -> Icons.Default.SentimentSatisfied
        else -> Icons.Default.SentimentVerySatisfied
    }

    IconButton(onClick = onClick) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(32.dp))
    }
}

@Composable
fun TabButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(if (isSelected) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
    }
}