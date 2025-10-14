package com.example.chuong3_3_1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chuong3_3_1.ui.theme.Chuong3_3_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chuong3_3_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column (
                modifier = modifier
                    .fillMaxSize()
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                ) {
                    Text("Button 1")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(start = 100.dp)
                ) {
                    Text("Button 2")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(start = 300.dp)
                ) {
                    Text("Button 3")
                }
                Text(
                    "Linear Layout",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                    )
            }
        }

        else -> {
            Row(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Button(
                    onClick = {}
                ) {
                    Text("Button 1")
                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 100.dp)
                ) {
                    Text("Button 2")
                }
            }
        }
    }
}