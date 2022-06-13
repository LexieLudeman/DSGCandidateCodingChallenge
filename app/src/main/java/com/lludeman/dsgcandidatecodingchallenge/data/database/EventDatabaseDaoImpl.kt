package com.lludeman.dsgcandidatecodingchallenge.data.database

class EventDatabaseDaoImpl {

    suspend fun checkIfFavorite(eventId: Int) : Boolean{
        if (eventsDao.checkIfEventFavorite(eventId) > 0) {
            return true
        }
        return false
    }
}