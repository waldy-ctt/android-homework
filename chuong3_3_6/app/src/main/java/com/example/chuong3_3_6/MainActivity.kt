package com.example.chuong3_3_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.chuong3_3_6.ui.theme.Chuong3_3_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chuong3_3_6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalInfoForm()
                }
            }
        }
    }
}

@Composable
fun PersonalInfoForm() {
    var fullName by remember { mutableStateOf("") }
    val genderOptions = listOf("Male", "Female", "Other")
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }
    val hobbies = listOf("Reading", "Gaming", "Sports")
    var selectedHobbies by remember { mutableStateOf(emptyList<String>()) }
    var result by remember { mutableStateOf("") }

    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val (
            fullNameLabel, fullNameField,
            genderLabel, genderGroup,
            hobbiesLabel, hobbiesGroup,
            displayButton, resultText
        ) = createRefs()

        Text("Full Name:", modifier = Modifier.constrainAs(fullNameLabel) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Enter your full name") },
            modifier = Modifier.constrainAs(fullNameField) {
                top.linkTo(fullNameLabel.bottom, margin = 4.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Text("Gender:", modifier = Modifier.constrainAs(genderLabel) {
            top.linkTo(fullNameField.bottom, margin = 16.dp)
            start.linkTo(parent.start)
        })

        Row(
            modifier = Modifier.constrainAs(genderGroup) {
                top.linkTo(genderLabel.bottom, margin = 4.dp)
                start.linkTo(parent.start)
            }
        ) {
            genderOptions.forEach { gender ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = (gender == selectedGender),
                            onClick = { selectedGender = gender }
                        )
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (gender == selectedGender),
                        onClick = { selectedGender = gender }
                    )
                    Text(
                        text = gender,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        Text("Hobbies:", modifier = Modifier.constrainAs(hobbiesLabel) {
            top.linkTo(genderGroup.bottom, margin = 16.dp)
            start.linkTo(parent.start)
        })

        Column(
            modifier = Modifier.constrainAs(hobbiesGroup) {
                top.linkTo(hobbiesLabel.bottom, margin = 4.dp)
                start.linkTo(parent.start)
            }
        ) {
            hobbies.forEach { hobby ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        selectedHobbies = if (selectedHobbies.contains(hobby)) {
                            selectedHobbies - hobby
                        } else {
                            selectedHobbies + hobby
                        }
                    }
                ) {
                    Checkbox(
                        checked = selectedHobbies.contains(hobby),
                        onCheckedChange = {
                            selectedHobbies = if (it) {
                                selectedHobbies + hobby
                            } else {
                                selectedHobbies - hobby
                            }
                        }
                    )
                    Text(text = hobby, modifier = Modifier.padding(start = 4.dp))
                }
            }
        }

        Button(
            onClick = {
                result = "Full Name: $fullName\n" +
                        "Gender: $selectedGender\n" +
                        "Hobbies: ${selectedHobbies.joinToString()}"
            },
            modifier = Modifier.constrainAs(displayButton) {
                top.linkTo(hobbiesGroup.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("Display")
        }

        Text(
            text = result,
            modifier = Modifier.constrainAs(resultText) {
                top.linkTo(displayButton.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Chuong3_3_6Theme {
        PersonalInfoForm()
    }
}
