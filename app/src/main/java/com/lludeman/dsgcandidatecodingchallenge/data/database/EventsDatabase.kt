package com.lludeman.dsgcandidatecodingchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EventEntity::class],
    version = 1
)

abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsDao
}