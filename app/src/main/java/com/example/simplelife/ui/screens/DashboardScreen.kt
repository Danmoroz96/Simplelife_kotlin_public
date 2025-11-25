package com.example.simplelife.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplelife.viewmodel.SimpleLifeViewModel

@Composable
fun DashboardScreen(viewModel: SimpleLifeViewModel) {
    val totalSpent by viewModel.totalSpending.collectAsState()
    val habits by viewModel.todayHabits.collectAsState()

    // State to control the Dialog visibility
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // --- GREETING ---
            Text(
                text = "Hello, SimpleLife!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- SPENDING CARD ---
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Total Spent Today", fontSize = 14.sp, color = Color.Gray)
                    Text(
                        text = "$${totalSpent ?: 0.0}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- HABITS SECTION ---
            Text(text = "Today's Habits", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (habits.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color(0xFFF5F5F5), MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No habits yet. Tap + to add one!", color = Color.Gray)
                }
            } else {
                LazyColumn {
                    items(habits) { habit ->
                        HabitRow(
                            name = habit.name,
                            isChecked = habit.isCompleted,
                            onCheckedChange = { viewModel.toggleHabit(habit) },
                            onDelete = { viewModel.deleteHabit(habit) }
                        )
                    }
                }
            }
        }

        // --- THE DIALOG (Updated with Mood Callback) ---
        if (showDialog) {
            AddEntryDialog(
                onDismiss = { showDialog = false },
                onAddExpense = { amount, cat, note ->
                    viewModel.addExpense(amount, cat, note)
                },
                onAddHabit = { name ->
                    viewModel.addHabit(name)
                },
                onAddMood = { rating, reflection ->
                    viewModel.addMood(rating, reflection)
                }
            )
        }
    }
}

@Composable
fun HabitRow(name: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
            Text(
                text = name,
                modifier = Modifier.weight(1f),
                textDecoration = if (isChecked) androidx.compose.ui.text.style.TextDecoration.LineThrough else null,
                color = if (isChecked) Color.Gray else Color.Black
            )
            IconButton(onClick = onDelete) {
                Text("✕", color = Color.Gray)
            }
        }
    }
}