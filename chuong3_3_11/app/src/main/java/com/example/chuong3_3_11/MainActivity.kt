package com.example.chuong3_3_11

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.chuong3_3_11.ui.theme.Chuong3_3_11Theme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chuong3_3_11Theme {
                TimePickerScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerScreen() {
    var selectedHour by remember { mutableStateOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) }
    var selectedMinute by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MINUTE)) }
    var imageToShow by remember { mutableStateOf(R.drawable.sun) } // Default image

    val timePickerState = rememberTimePickerState(selectedHour, selectedMinute, is24Hour = true)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TimePicker(
            state = timePickerState,
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = {
            val hour = timePickerState.hour
            imageToShow = if (hour in 6..18) {
                R.drawable.sun
            } else {
                R.drawable.moon
            }
        }) {
            Text("Set Time")
        }

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        Image(
            painter = rememberAsyncImagePainter(imageToShow, imageLoader),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}
