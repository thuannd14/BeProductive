package com.ndt.beproductive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ndt.beproductive.R
import live.videosdk.rtc.android.Meeting
import live.videosdk.rtc.android.Participant
import live.videosdk.rtc.android.Stream
import live.videosdk.rtc.android.VideoView
import live.videosdk.rtc.android.listeners.MeetingEventListener
import live.videosdk.rtc.android.listeners.ParticipantEventListener
import org.webrtc.VideoTrack

class PersonAdapter(meeting: Meeting) : RecyclerView.Adapter<PersonAdapter.PersonHolder>() {
    private val participantsList: MutableList<Participant> = ArrayList()/*
        Khối init sẽ được gọi ngay sau khi một đối tượng của lớp được tạo, và trước khi bất kỳ lệnh nào khác trong khối thân của lớp đó được thực thi.
    */

    init {
        participantsList.add(meeting.localParticipant)
        meeting.addEventListener(object : MeetingEventListener() {
            override fun onParticipantJoined(participant: Participant) {
                // add participant to the list
                participantsList.add(participant)
                notifyItemInserted(participantsList.size - 1)
            }

            override fun onParticipantLeft(participant: Participant) {
                var pos = -1
                for (i in participantsList.indices) {
                    if (participantsList[i].id == participant.id) {
                        pos = i
                        break
                    }
                }
                // remove participant from the list
                participantsList.remove(participant)
                if (pos >= 0) {
                    notifyItemRemoved(pos)
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.PersonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonHolder(view)
    }

    override fun onBindViewHolder(holder: PersonAdapter.PersonHolder, position: Int) {
        val participant = participantsList[position]

        holder.tvName.text = participant.displayName

        for ((_, stream) in participant.streams) {
            if (stream.kind.equals("video", ignoreCase = true)) {
                holder.participantView.visibility = View.VISIBLE
                val videoTrack = stream.track as VideoTrack
                holder.participantView.addTrack(videoTrack)
                break
            }
        }

        participant.addEventListener(object : ParticipantEventListener() {
            override fun onStreamEnabled(stream: Stream?) {
                if (stream?.kind.equals("video", ignoreCase = true)) {
                    holder.participantView.visibility = View.VISIBLE
                    val videoTrack = stream?.track as VideoTrack
                    holder.participantView.addTrack(videoTrack)
                }
            }

            override fun onStreamDisabled(stream: Stream?) {
                if (stream?.kind.equals("video", ignoreCase = true)) {
                    holder.participantView.removeTrack()
                    holder.participantView.visibility = View.GONE
                }
            }
        })

    }

    override fun getItemCount(): Int {
        return participantsList.size
    }

    inner class PersonHolder(view: View) : ViewHolder(view) {
        var participantView: VideoView = view.findViewById(R.id.participantView)
        var tvName: TextView = view.findViewById(R.id.tvName)
    }

}