package com.example.chuong3_3_9

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chuong3_3_9.ui.theme.Chuong3_3_9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chuong3_3_9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SurveyScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SurveyScreen(modifier: Modifier = Modifier) {
    var readingBook by remember { mutableStateOf(false) }
    var watchingMovie by remember { mutableStateOf(false) }
    var traveling by remember { mutableStateOf(false) }
    val radioOptions = listOf("Good", "Decent", "Bad")
    var selectedRating by remember { mutableStateOf(radioOptions[0]) }
    val context = LocalContext.current

    Column(modifier = modifier.padding(16.dp)) {
        Text("Hobbies:")
        Row(modifier = Modifier.padding(start = 16.dp)) {
            Checkbox(
                checked = readingBook,
                onCheckedChange = { readingBook = it }
            )
            Text("Reading Book")
        }
        Row(modifier = Modifier.padding(start = 16.dp)) {
            Checkbox(
                checked = watchingMovie,
                onCheckedChange = { watchingMovie = it }
            )
            Text("Watching Movie")
        }
        Row(modifier = Modifier.padding(start = 16.dp)) {
            Checkbox(
                checked = traveling,
                onCheckedChange = { traveling = it }
            )
            Text("Traveling")
        }

        Text("Rate our service:")
        radioOptions.forEach { rating ->
            Row(modifier = Modifier.padding(start = 16.dp)) {
                RadioButton(
                    selected = (rating == selectedRating),
                    onClick = { selectedRating = rating }
                )
                Text(text = rating)
            }
        }

        Button(onClick = {
            val hobbies = mutableListOf<String>()
            if (readingBook) hobbies.add("Reading Book")
            if (watchingMovie) hobbies.add("Watching Movie")
            if (traveling) hobbies.add("Traveling")

            val message = "Hobbies: ${hobbies.joinToString()}, Rating: $selectedRating"
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }) {
            Text("Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Chuong3_3_9Theme {
        SurveyScreen()
    }
}