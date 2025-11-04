package com.example.chuong3_3_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.chuong3_3_7.ui.theme.Chuong3_3_7Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chuong3_3_7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DatePickerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen(modifier: Modifier = Modifier) {
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf("Selected date will be shown here") }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (dateText, datePicker, confirmButton) = createRefs()

        Text(
            text = selectedDate,
            modifier = Modifier.constrainAs(dateText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        DatePicker(
            state = datePickerState,
            modifier = Modifier.constrainAs(datePicker) {
                top.linkTo(dateText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = {
                datePickerState.selectedDateMillis?.let {
                    selectedDate = formatter.format(Date(it))
                }
            },
            modifier = Modifier.constrainAs(confirmButton) {
                top.linkTo(datePicker.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("Confirm")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerScreenPreview() {
    Chuong3_3_7Theme {
        DatePickerScreen()
    }
}
