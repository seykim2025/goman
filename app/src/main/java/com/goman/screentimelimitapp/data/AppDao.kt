package com.goman.screentimelimitapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM app_limits")
    fun getAllApps(): Flow<List<AppEntity>>

    @Query("SELECT * FROM app_limits WHERE packageName = :packageName")
    suspend fun getApp(packageName: String): AppEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(app: AppEntity)

    @Update
    suspend fun updateApp(app: AppEntity)

    @Query("UPDATE app_limits SET usedMinutesToday = 0, extensionCount = 0 WHERE usedMinutesToday > 0")
    suspend fun resetDailyUsage()
}
