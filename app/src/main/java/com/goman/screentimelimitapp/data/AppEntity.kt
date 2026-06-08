package com.goman.screentimelimitapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_limits")
data class AppEntity(
    @PrimaryKey val packageName: String,
    val isBlacklisted: Boolean = true,
    val isWhitelisted: Boolean = false,
    val dailyLimitMinutes: Int = 0,
    val usedMinutesToday: Int = 0,
    val lastExtendedTime: Long = 0,
    val extensionCount: Int = 0 // 연장 횟수 (스택)
)
