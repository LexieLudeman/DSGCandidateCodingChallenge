package com.lludeman.dsgcandidatecodingchallenge.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Event(
    @SerializedName("id")
    var id: Int,

    @SerializedName("venue")
    @Expose
    var venue: Venue,

    @SerializedName("datetime_tbd")
    @Expose
    var isDatetimeTbd: Boolean,

    @SerializedName("performers")
    @Expose
    var performers: List<Performer>,

    @SerializedName("datetime_local")
    @Expose
    var datetimeLocal: String?,

    @SerializedName("title")
    @Expose
    var title: String?

) : Serializable