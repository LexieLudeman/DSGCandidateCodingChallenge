package com.lludeman.dsgcandidatecodingchallenge.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.lludeman.dsgcandidatecodingchallenge.R
import com.lludeman.dsgcandidatecodingchallenge.common.Event
import com.lludeman.dsgcandidatecodingchallenge.viewmodel.MainViewModel
import com.squareup.picasso.Picasso


class EventAdapter(
    private var allEvents: List<Event>,
    mainViewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val eventKey = "EVENT_KEY"
    private val picasso = Picasso.get()

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleText: TextView = view.findViewById(R.id.title)
        private val locationText: TextView = view.findViewById(R.id.location)
        private val imageView: ImageView = view.findViewById(R.id.image)
        private val dateText: TextView = view.findViewById(R.id.time)

        fun bind(event : Event) {
            titleText.text = event.title
            locationText.text = event.venue.displayLocation
            picasso.load(event.performers[0].image).into(imageView)
            if (event.isDatetimeTbd) {
                dateText.text = "TBD"
            } else {
                dateText.text = event.datetimeLocal
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
            var touchedEvent = allEvents[position]
            var intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra(eventKey, touchedEvent)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = allEvents.size

    fun updateItems(events: List<Event>) {
        allEvents = events
        notifyDataSetChanged()
    }

}


