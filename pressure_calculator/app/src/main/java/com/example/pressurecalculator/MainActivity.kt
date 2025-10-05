package com.example.pressurecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PressureCalculatorApp()
        }
    }
}

@Composable
fun PressureCalculatorApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PressureCalculatorScreen()
        }
    }
}

@Composable
fun PressureCalculatorScreen() {
    var p1Text by remember { mutableStateOf(TextFieldValue()) }
    var t1Text by remember { mutableStateOf(TextFieldValue()) }
    var t2Text by remember { mutableStateOf(TextFieldValue()) }
    var result by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = p1Text,
            onValueChange = { p1Text = it },
            label = { Text("Введите p1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = t1Text,
            onValueChange = { t1Text = it },
            label = { Text("Введите t1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = t2Text,
            onValueChange = { t2Text = it },
            label = { Text("Введите t2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                val p1 = p1Text.text.toDoubleOrNull()
                val t1 = t1Text.text.toDoubleOrNull()
                val t2 = t2Text.text.toDoubleOrNull()

                result = if (p1 != null && t1 != null && t2 != null && t1 != 0.0) {
                    val p2 = (t2 + 273) * (p1 / (t1 + 273))
                    "Результат p2 = $p2"
                } else {
                    "Некорректный ввод или t1 = 0"
                }

                focusManager.clearFocus()
            }
        ) {
            Text("Вычислить")
        }
        Spacer(Modifier.height(16.dp))
        Text(text = result)
    }
}
