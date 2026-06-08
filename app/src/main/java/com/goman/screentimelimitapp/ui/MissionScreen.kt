package com.goman.screentimelimitapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MissionScreen(onMissionSuccess: () -> Unit, onMissionFail: () -> Unit) {
    var answer by remember { mutableStateOf("") }
    
    // 임시 연산 미션 로직 (실제로는 난이도 스케일링 필요)
    val num1 = 123
    val num2 = 456
    val correctAnswer = (num1 + num2).toString()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "연장 미션: 연산 풀기", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "$num1 + $num2 = ?", style = MaterialTheme.typography.headlineLarge)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("정답 입력") }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = {
            if (answer == correctAnswer) {
                onMissionSuccess()
            } else {
                onMissionFail()
            }
        }) {
            Text("정답 제출")
        }
    }
}
