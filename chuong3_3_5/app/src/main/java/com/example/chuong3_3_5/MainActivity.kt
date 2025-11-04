package com.example.chuong3_3_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chuong3_3_5.ui.theme.Chuong3_3_5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chuong3_3_5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Number 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Number 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                val n1 = number1.toDoubleOrNull()
                val n2 = number2.toDoubleOrNull()
                if (n1 != null && n2 != null) {
                    result = (n1 + n2).toString()
                } else {
                    result = "Invalid input"
                }
            }) {
                Text("+")
            }
            Button(onClick = {
                val n1 = number1.toDoubleOrNull()
                val n2 = number2.toDoubleOrNull()
                if (n1 != null && n2 != null) {
                    result = (n1 - n2).toString()
                } else {
                    result = "Invalid input"
                }
            }) {
                Text("-")
            }
            Button(onClick = {
                val n1 = number1.toDoubleOrNull()
                val n2 = number2.toDoubleOrNull()
                if (n1 != null && n2 != null) {
                    result = (n1 * n2).toString()
                } else {
                    result = "Invalid input"
                }
            }) {
                Text("*")
            }
            Button(onClick = {
                val n1 = number1.toDoubleOrNull()
                val n2 = number2.toDoubleOrNull()
                if (n1 != null && n2 != null) {
                    if (n2 != 0.0) {
                        result = (n1 / n2).toString()
                    } else {
                        result = "Cannot divide by zero"
                    }
                } else {
                    result = "Invalid input"
                }
            }) {
                Text("/")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $result")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Chuong3_3_5Theme {
        CalculatorApp()
    }
}
