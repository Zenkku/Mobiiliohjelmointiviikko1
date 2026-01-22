package com.example.viikko1tehtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.viikko1tehtv.screens.HomeScreen
import com.example.viikko1tehtv.ui.theme.Viikko1tehtäväTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Viikko1tehtäväTheme(dynamicColor = false) { // Dynaaminen väri pois päältä
                Surface {
                    HomeScreen()
                }
            }
        }
    }
}
