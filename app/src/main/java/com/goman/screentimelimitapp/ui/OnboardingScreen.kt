package com.goman.screentimelimitapp.ui

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.goman.screentimelimitapp.core.AppLockAccessibilityService
import com.goman.screentimelimitapp.core.LockAdminReceiver
import com.goman.screentimelimitapp.core.PermissionUtil

@Composable
fun OnboardingScreen(onPermissionsGranted: () -> Unit) {
    val context = LocalContext.current
    val overlayGranted = PermissionUtil.isOverlayPermissionGranted(context)
    val accessibilityGranted = PermissionUtil.isAccessibilityServiceEnabled(context, AppLockAccessibilityService::class.java)
    val deviceAdminGranted = PermissionUtil.isDeviceAdminActive(context, LockAdminReceiver::class.java)
    val usageStatsGranted = PermissionUtil.isUsageStatsPermissionGranted(context)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "권한 설정 안내", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "앱을 정상적으로 사용하기 위해 다음 권한이 필요합니다.")
        Spacer(modifier = Modifier.height(24.dp))

        PermissionItem(
            name = "1. 다른 앱 위에 표시 (잠금 화면용)",
            isGranted = overlayGranted,
            onClick = {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
                context.startActivity(intent)
            }
        )
        
        PermissionItem(
            name = "2. 접근성 권한 (꼼수 방지용)",
            isGranted = accessibilityGranted,
            onClick = {
                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                context.startActivity(intent)
            }
        )

        PermissionItem(
            name = "3. 기기 관리자 권한 (앱 삭제 방지용)",
            isGranted = deviceAdminGranted,
            onClick = {
                val componentName = ComponentName(context, LockAdminReceiver::class.java)
                val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                    putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
                    putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "스크린타임 앱의 임의 삭제를 방지합니다.")
                }
                context.startActivity(intent)
            }
        )

        PermissionItem(
            name = "4. 사용 정보 접근 권한 (앱 사용 시간 측정용)",
            isGranted = usageStatsGranted,
            onClick = {
                val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                context.startActivity(intent)
            }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { onPermissionsGranted() },
            enabled = overlayGranted && accessibilityGranted && deviceAdminGranted && usageStatsGranted
        ) {
            Text("모두 허용 완료! 시작하기")
        }
    }
}

@Composable
fun PermissionItem(name: String, isGranted: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name, modifier = Modifier.weight(1f))
        Button(onClick = onClick, enabled = !isGranted) {
            Text(if (isGranted) "완료" else "설정")
        }
    }
}
