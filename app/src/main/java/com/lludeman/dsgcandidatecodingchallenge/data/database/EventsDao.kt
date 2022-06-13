package com.lludeman.dsgcandidatecodingchallenge.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventsDao {
    @Query("SELECT COUNT (*) FROM EventEntity where id = :id")
    suspend fun checkIfEventFavorite(id: Int): Int

    @Insert
    suspend fun insertEvent(event: EventEntity)

    @Delete
    suspend fun deleteEvent(event: EventEntity)
}