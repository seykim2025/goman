package com.goman.screentimelimitapp.core

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class AppLockAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
        info.flags = AccessibilityServiceInfo.DEFAULT
        serviceInfo = info
        Log.d("AppLock", "Accessibility Service Connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        val packageName = event.packageName?.toString() ?: return
        val className = event.className?.toString() ?: return

        // 꼼수 방지: 사용자가 '설정' 앱에서 앱 삭제, 강제 종료 등을 시도하는지 감지
        if (packageName == "com.android.settings") {
            // 안드로이드 설정 창의 상세 정보나 기기 관리자 메뉴에 진입했을 때
            if (className.contains("InstalledAppDetails") || 
                className.contains("DeviceAdminSettings") ||
                className.contains("ManageApplications")) {
                
                // 설정 창을 강제로 종료하거나 홈으로 튕겨냅니다
                performGlobalAction(GLOBAL_ACTION_HOME)
                Toast.makeText(this, "접근이 차단되었습니다 (안티 치트 가동 중)", Toast.LENGTH_SHORT).show()
                Log.d("AppLock", "Blocked settings access: $className")
            }
        }
    }

    override fun onInterrupt() {
        Log.e("AppLock", "Accessibility Service Interrupted")
    }
}
