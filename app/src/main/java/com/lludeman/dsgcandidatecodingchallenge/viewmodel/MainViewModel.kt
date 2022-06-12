package com.lludeman.dsgcandidatecodingchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lludeman.dsgcandidatecodingchallenge.respository.EventRepository
import com.lludeman.dsgcandidatecodingchallenge.common.Event
import kotlinx.coroutines.launch

class MainViewModel (
    private val eventRepository: EventRepository
): ViewModel(){

    private val _eventData: MutableLiveData<List<Event>> = MutableLiveData()
    val eventData: LiveData<List<Event>> = _eventData

    fun getEventData() {
        viewModelScope.launch {
            val events = eventRepository.getEvents()
            _eventData.value = events
        }
    }
}