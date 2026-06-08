package com.goman.screentimelimitapp.core

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class LockAdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Toast.makeText(context, "기기 관리자 권한이 활성화되었습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onDisableRequested(context: Context, intent: Intent): CharSequence {
        // 기기 관리자 해제 시도 시 경고 메시지 표시 (접근성 서비스와 연계하여 방어)
        return "스크린타임 제한 앱의 기기 관리자 권한을 해제하시겠습니까? (안티 치트 작동 중)"
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Toast.makeText(context, "기기 관리자 권한이 해제되었습니다.", Toast.LENGTH_SHORT).show()
    }
}
