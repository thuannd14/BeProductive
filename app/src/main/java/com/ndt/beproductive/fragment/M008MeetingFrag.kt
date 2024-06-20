package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.R
import com.ndt.beproductive.adapter.PersonAdapter
import com.ndt.beproductive.databinding.M008MeetingFragBinding
import com.ndt.beproductive.viewmodel.M008MeetingVM
import live.videosdk.rtc.android.Meeting
import live.videosdk.rtc.android.Participant
import live.videosdk.rtc.android.VideoSDK
import live.videosdk.rtc.android.listeners.MeetingEventListener

class M008MeetingFrag : BaseFrag<M008MeetingFragBinding, M008MeetingVM>() {
    companion object {
        val TAG: String = M008MeetingFrag::class.java.name
    }

    private var meetingRoom: Meeting? = null
    private var micEnabled = true
    private var webcamEnabled = true

    private lateinit var userName: String
    private lateinit var token: String
    private lateinit var meetingID: String

    private val meetingEventListener = object : MeetingEventListener() {
        override fun onMeetingJoined() {
            Log.i(TAG, "onMeetingJoined()")
        }

        override fun onMeetingLeft() {
            Log.i(TAG, "onMeetingLeft()")
            meetingRoom = null
            if (!isRemoving) {
                mCallBack.showFrag(M008JoinFrag.TAG, null, false)
            }
        }

        override fun onParticipantJoined(participant: Participant) {
            Toast.makeText(
                mContext, "${participant.displayName} joined", Toast.LENGTH_SHORT
            ).show()
        }

        override fun onParticipantLeft(participant: Participant) {
            Toast.makeText(
                mContext, "${participant.displayName} left", Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun initViews() {
        token = viewModel.getToken()
        meetingID = mdata as String
        userName = CommonUtils.getPref(USER_NAME).toString()
        binding.tvMeetingRoom.text = String.format("Meeting room:%s", meetingID)

        Log.i(TAG, "TOKEN: $token\nmeetingID: $meetingID")

//        VideoSDK.config(token)
//        meetingRoom = VideoSDK.initMeeting(
//            mContext, meetingID, userName, micEnabled, webcamEnabled, null, null, false, null, null
//        )
//
//        meetingRoom?.addEventListener(meetingEventListener)
//        meetingRoom?.join()

        setUpMeeting()
        setActionListeners()

        binding.rvMeeting.layoutManager = GridLayoutManager(mContext, 2)
        binding.rvMeeting.adapter = PersonAdapter(meetingRoom!!)

    }

    private fun setUpMeeting() {
        VideoSDK.config(token)
        meetingRoom = VideoSDK.initMeeting(
            mContext, meetingID, userName, micEnabled, webcamEnabled, null, null, false, null, null
        )

        meetingRoom?.addEventListener(meetingEventListener)
        meetingRoom?.join()
    }

    private fun setActionListeners() {
        binding.ivMic.setOnClickListener {
            if (micEnabled) {
                meetingRoom?.muteMic()
                binding.ivMic.setImageResource(R.mipmap.ic_mute)
                Toast.makeText(mContext, "Mic Disabled", Toast.LENGTH_SHORT).show()
            } else {
                meetingRoom?.unmuteMic()
                binding.ivMic.setImageResource(R.mipmap.ic_mic)
                Toast.makeText(mContext, "Mic Enabled", Toast.LENGTH_SHORT).show()
            }
            micEnabled = !micEnabled
            Log.i(TAG, "MIC: $micEnabled")
        }

        binding.ivWebcam.setOnClickListener {
            if (webcamEnabled) {
                meetingRoom?.disableWebcam()
                binding.ivWebcam.setImageResource(R.mipmap.ic_cam_off)
                Toast.makeText(mContext, "Webcam Disabled", Toast.LENGTH_SHORT).show()
            } else {
                meetingRoom?.enableWebcam()
                binding.ivWebcam.setImageResource(R.mipmap.ic_webcam)
                Toast.makeText(mContext, "Webcam Enabled", Toast.LENGTH_SHORT).show()
            }
            webcamEnabled = !webcamEnabled
            Log.i(TAG, "Camera: $webcamEnabled")
        }

        binding.ivLeave.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    mContext, androidx.appcompat.R.anim.abc_fade_in
                )
            )
            meetingRoom?.leave()
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M008MeetingFragBinding {
        return M008MeetingFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M008MeetingVM> {
        return M008MeetingVM::class.java
    }
}
