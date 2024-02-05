package com.example.sciflare_task.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sciflare_task.model.Model

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(details: List<Model?>?)

    @Query("SELECT DISTINCT * FROM details")
    fun getDetails(): LiveData<List<Model>>

    @Query("DELETE FROM details")
    fun deleteAll()
}