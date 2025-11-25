package com.example.simplelife.ui.screens

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.simplelife.viewmodel.SimpleLifeViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun StatsScreen(viewModel: SimpleLifeViewModel) {
    val expenses by viewModel.expenses.collectAsState()

    // 1. Logic: Group expenses by Category and Sum them up
    val chartData = remember(expenses) {
        expenses.groupBy { it.category }
            .mapValues { entry -> entry.value.sumOf { it.amount } }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Spending Breakdown",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (expenses.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No data to show yet!", color = Color.Gray)
            }
        } else {
            // 2. The Chart View
            AndroidView(
                factory = { context ->
                    PieChart(context).apply {
                        description.isEnabled = false
                        isDrawHoleEnabled = true
                        setHoleColor(AndroidColor.TRANSPARENT)
                        setUsePercentValues(true)
                        setEntryLabelColor(AndroidColor.BLACK)
                        setCenterText("Expenses")
                        setCenterTextSize(18f)
                        legend.isEnabled = true
                    }
                },
                update = { pieChart ->
                    // 3. Convert our data to MPAndroidChart format
                    val entries = chartData.map { (category, amount) ->
                        PieEntry(amount.toFloat(), category)
                    }

                    val dataSet = PieDataSet(entries, "Categories").apply {
                        colors = ColorTemplate.MATERIAL_COLORS.toList()
                        valueTextSize = 16f
                        valueTextColor = AndroidColor.WHITE
                        sliceSpace = 3f
                    }

                    val pieData = PieData(dataSet)
                    pieChart.data = pieData
                    pieChart.animateY(1400) // Animation!
                    pieChart.invalidate()   // Refresh
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
        }
    }
}