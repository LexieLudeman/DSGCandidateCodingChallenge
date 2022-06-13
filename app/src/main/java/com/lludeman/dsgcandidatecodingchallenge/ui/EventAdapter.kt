package com.lludeman.dsgcandidatecodingchallenge.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lludeman.dsgcandidatecodingchallenge.R
import com.lludeman.dsgcandidatecodingchallenge.common.Event
import com.lludeman.dsgcandidatecodingchallenge.data.database.EventsDao
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class EventAdapter(
    private var allEvents: List<Event>,
    private val eventsDao: EventsDao
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val eventKey = "EVENT_KEY"
    private val picasso = Picasso.get()

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleText: TextView = view.findViewById(R.id.title)
        private val locationText: TextView = view.findViewById(R.id.location)
        private val imageView: ImageView = view.findViewById(R.id.image)
        private val dateText: TextView = view.findViewById(R.id.time)
        private val favoriteView: ImageView = view.findViewById(R.id.card_favorite)

        fun bind(event : Event) {
            titleText.text = event.title
            locationText.text = event.venue.displayLocation
            picasso
                .load(event.performers[0].image)
                .transform(RoundedTransformation(10,0))
                .resize(225, 225)
                .centerCrop()
                .into(imageView)

            if (event.isDatetimeTbd) {
                dateText.text = "TBD"
            } else {
                dateText.text = getDate(event.datetimeLocal.toString())
            }

            GlobalScope.launch(Dispatchers.Main) {
                val favorited = withContext(Dispatchers.IO) { checkIfFavorite(event.id) }
                if (favorited) {
                    favoriteView.visibility = View.VISIBLE
                }
                else {
                    favoriteView.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(event = allEvents[position])
        holder.itemView.setOnClickListener {
            val touchedEvent = allEvents[position]
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra(eventKey, touchedEvent)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = allEvents.size

    fun updateItems(events: List<Event>) {
        allEvents = events
        notifyDataSetChanged()
    }

    fun getDate(dateStr: String) : String{
        val currentFormat = SimpleDateFormat("yyyy-MM-DD HH:MM:SS")
        return try {
            val date = currentFormat.parse(dateStr.replace('T', ' '))
            val formatter = SimpleDateFormat("EEE, MMM dd yyyy h:mm a", Locale.ENGLISH)
            (formatter.format(date))
        } catch (e: Exception){
            Log.d("Exception",e.toString())
            dateStr
        }
    }

    suspend fun checkIfFavorite(eventId: Int) : Boolean{
        if (eventsDao.checkIfEventFavorite(eventId) > 0) {
            return true
        }
        return false
    }
}


