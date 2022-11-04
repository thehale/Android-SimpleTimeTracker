package com.example.util.simpletimetracker.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.util.simpletimetracker.data_local.model.ActivityFilterDBO

@Dao
interface ActivityFilterDao {

    @Transaction
    @Query("SELECT * FROM activityFilters")
    suspend fun getAll(): List<ActivityFilterDBO>

    @Transaction
    @Query("SELECT * FROM activityFilters WHERE id = :id LIMIT 1")
    suspend fun get(id: Long): ActivityFilterDBO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activityFilter: ActivityFilterDBO): Long

    @Query("UPDATE activityFilters SET selected =:selected  WHERE id = :id")
    suspend fun changeSelected(id: Long, selected: Int)

    @Query("DELETE FROM activityFilters WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM activityFilters")
    suspend fun clear()
}