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
fun OnboardingScreen(onPermissionsGranted: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "권한 설정 안내", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "앱을 정상적으로 사용하기 위해 다음 권한이 필요합니다.")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "1. 다른 앱 위에 표시 (잠금 화면용)")
        Text(text = "2. 접근성 권한 (꼼수 방지용)")
        Text(text = "3. 기기 관리자 권한 (앱 삭제 방지용)")
        Text(text = "4. 사용 정보 접근 권한 (앱 사용 시간 측정용)")
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = {
            // TODO: 실제 권한 요청 로직 추가
            onPermissionsGranted()
        }) {
            Text("권한 허용하러 가기")
        }
    }
}
