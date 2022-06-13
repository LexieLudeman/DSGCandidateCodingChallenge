package com.lludeman.dsgcandidatecodingchallenge

import android.app.Application
import androidx.room.Room
import com.lludeman.dsgcandidatecodingchallenge.data.database.EventsDatabase

class CodingChallengeApplication: Application() {

    lateinit var database: EventsDatabase

    override fun onCreate() {
        super.onCreate()
        createDatabase()
    }

    private fun createDatabase() {
        database = Room.databaseBuilder(
            applicationContext,
            EventsDatabase::class.java, "events-database"
        ).build()
    }
}