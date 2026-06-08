package com.goman.screentimelimitapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainDashboardScreen(onLockTestClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "오늘의 스크린타임", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "총 사용 시간: 1시간 30분", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = { /* TODO: 블랙리스트 설정 화면으로 이동 */ }) {
            Text("앱 블랙리스트 설정")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = onLockTestClick) {
            Text("잠금 화면(오버레이) 테스트")
        }
    }
}
