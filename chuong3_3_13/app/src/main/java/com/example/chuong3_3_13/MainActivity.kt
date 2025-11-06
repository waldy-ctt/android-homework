package com.example.chuong3_3_13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.chuong3_3_13.ui.theme.Chuong3_3_13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chuong3_3_13Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Task(val title: String, var isCompleted: Boolean)

@Composable
fun TodoScreen(modifier: Modifier = Modifier) {
    var taskTitle by remember { mutableStateOf("") }
    var taskCompleted by remember { mutableStateOf(false) }
    var taskList by remember { mutableStateOf(listOf<Task>()) }

    ConstraintLayout(modifier = modifier.fillMaxSize().padding(16.dp)) {
        val (title, checkbox, addButton, taskListText) = createRefs()

        OutlinedTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            label = { Text("Task Title") },
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Checkbox(
            checked = taskCompleted,
            onCheckedChange = { taskCompleted = it },
            modifier = Modifier.constrainAs(checkbox) {
                top.linkTo(title.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )

        Button(
            onClick = {
                if (taskTitle.isNotBlank()) {
                    taskList = taskList + Task(taskTitle, taskCompleted)
                    taskTitle = ""
                    taskCompleted = false
                }
            },
            modifier = Modifier.constrainAs(addButton) {
                top.linkTo(checkbox.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("Add Task")
        }

        LazyColumn(
            modifier = Modifier.constrainAs(taskListText) {
                top.linkTo(addButton.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            items(taskList) { task ->
                Text("Task: ${task.title}, Completed: ${task.isCompleted}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    Chuong3_3_13Theme {
        TodoScreen()
    }
}
