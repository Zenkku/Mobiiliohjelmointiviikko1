package com.example.viikko1tehtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viikko1tehtv.navigation.ROUTE_CALENDER
import com.example.viikko1tehtv.navigation.ROUTE_HOME
import com.example.viikko1tehtv.navigation.ROUTE_SETTINGS
import com.example.viikko1tehtv.view.CalendarScreen
import com.example.viikko1tehtv.view.HomeScreen
import com.example.viikko1tehtv.view.SettingsScreen
import com.example.viikko1tehtv.viewModel.TaskViewModel
import com.example.viikko1tehtv.ui.theme.Viikko1tehtäväTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: TaskViewModel = viewModel()

            Viikko1tehtäväTheme(dynamicColor = false) { // Dynaaminen väri pois päältä
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_HOME
                    ) {
                        composable(ROUTE_HOME) {
                            HomeScreen(
                                viewModel = viewModel,
                                onNavigationToCaledar = {
                                    navController.navigate(ROUTE_CALENDER)
                                },
                                onNavigationToSettings = {
                                    navController.navigate(ROUTE_SETTINGS)
                                }
                            )
                        }
                        composable(ROUTE_CALENDER) {
                            CalendarScreen(
                                viewModel = viewModel,
                                onNavigationToHome = {
                                    navController.navigate(ROUTE_HOME)
                                }
                            )
                        }
                        composable(ROUTE_SETTINGS) {
                            SettingsScreen(
                                onNavigationToHome = {
                                    navController.navigate(ROUTE_HOME)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
