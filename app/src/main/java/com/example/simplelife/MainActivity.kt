package com.example.simplelife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// The line below fixes the "Red" error!
import androidx.compose.foundation.layout.* import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
// Imports for your custom screens
import com.example.simplelife.ui.screens.DashboardScreen
import com.example.simplelife.ui.screens.StatsScreen
import com.example.simplelife.ui.theme.SimpleLifeTheme
import com.example.simplelife.viewmodel.SimpleLifeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleLifeTheme {
                // Initialize the ViewModel
                val viewModel: SimpleLifeViewModel = viewModel(factory = SimpleLifeViewModel.Factory)

                // Load the Main App
                SimpleLifeApp(viewModel)
            }
        }
    }
}

@Composable
fun SimpleLifeApp(viewModel: SimpleLifeViewModel) {
    val navController = rememberNavController()

    // Define the screens for the Bottom Bar
    val items = listOf(Screen.Dashboard, Screen.Stats, Screen.Settings)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.route) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Dashboard.route,
            Modifier.padding(innerPadding)
        ) {
            // 1. DASHBOARD (Home)
            composable(Screen.Dashboard.route) {
                DashboardScreen(viewModel)
            }

            // 2. STATS (Pie Chart)
            composable(Screen.Stats.route) {
                StatsScreen(viewModel)
            }

            // 3. SETTINGS (Placeholder)
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

// --- NAVIGATION ROUTES ---
sealed class Screen(val route: String, val icon: ImageVector) {
    object Dashboard : Screen("Dashboard", Icons.Filled.Home)
    object Stats : Screen("Stats", Icons.Filled.BarChart)
    object Settings : Screen("Settings", Icons.Filled.Settings)
}

// --- SETTINGS PLACEHOLDER ---
@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), // This should now be working!
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings coming soon!")
    }
}