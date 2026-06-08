package com.goman.screentimelimitapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OverlayLockScreen(onMissionClick: () -> Unit, onEmergencyCoinClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xDD000000))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "제한 시간 초과",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "더 이상 스마트폰을 사용할 수 없습니다.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onMissionClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("고통스러운 미션 수행하고 5분 연장하기")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onEmergencyCoinClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("긴급 탈출 코인 사용 (남은 수량: 3)")
        }
    }
}
