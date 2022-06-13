package com.lludeman.dsgcandidatecodingchallenge.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = false) val id: Int
)
