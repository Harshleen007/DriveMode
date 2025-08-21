package com.example.drivermodefeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrivingModeUI()
        }
    }
}

@Composable
fun DrivingModeUI() {
    var selectedMode by remember { mutableStateOf("Dynamic Feature") }

    val modes = listOf("ECO", "SPORTS", "COMFORT", "INDIVIDUAL", "TERRAIN")

    // Pick image based on selected mode
    val imageRes = when (selectedMode) {
        "ECO" -> R.drawable.car_eco
        "SPORTS" -> R.drawable.car_sports
        "COMFORT" -> R.drawable.car_comfort
        "INDIVIDUAL" -> R.drawable.car_eco
        "TERRAIN" -> R.drawable.car_sports
        else -> R.drawable.car_sports
    }

    // Fullscreen layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // fallback background
        contentAlignment = Alignment.Center
    ) {
        // Fullscreen background image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Car Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Foreground UI (full width/height overlay)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f)) // semi-transparent overlay
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Side Buttons
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                modes.forEach { mode ->
                    Text(
                        text = mode,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .clickable { selectedMode = mode }
                            .padding(8.dp)
                    )
                }
            }

            // Right Side Dynamic Text
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedMode,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
        }
    }
}
