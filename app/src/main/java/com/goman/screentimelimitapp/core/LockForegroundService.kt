package com.goman.screentimelimitapp.core

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class LockForegroundService : Service() {
    companion object {
        const val CHANNEL_ID = "LockForegroundServiceChannel"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("스크린타임 제한 작동 중")
            .setContentText("안티 치트 기능이 백그라운드에서 실행 중입니다.")
            .setSmallIcon(android.R.drawable.ic_secure) // TODO: 커스텀 아이콘
            .setOngoing(true)
            .build()

        startForeground(NOTIFICATION_ID, notification)
        
        // 서비스가 강제 종료되어도 OS가 다시 시작하도록 함
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Screen Time Lock Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }
}
