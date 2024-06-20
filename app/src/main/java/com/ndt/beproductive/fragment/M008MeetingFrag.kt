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

    private var userName = ""
    private var token = ""
    private var meetingID = ""
    override fun initViews() {
        token = viewModel.getToken()
        meetingID = mdata as String
        userName = CommonUtils.getPref(USER_NAME).toString()
        Log.i(TAG, "TOKEN: $token\n meetingID$meetingID")

        setupMeeting()
        setActionListeners()

        binding.rvMeeting.layoutManager = GridLayoutManager(mContext, 2)
        binding.rvMeeting.adapter = PersonAdapter(meetingRoom!!)
    }

    private fun setActionListeners() {
        // click mic
        binding.ivMic.setOnClickListener { v ->
            if (micEnabled) {
                // this will mute the local participant's mic
                meetingRoom?.muteMic()
                binding.ivMic.setImageResource(R.mipmap.ic_mute)
                Toast.makeText(mContext, "Mic Disabled", Toast.LENGTH_SHORT).show()
            } else {
                // this will unmute the local participant's mic
                meetingRoom?.unmuteMic()
                binding.ivMic.setImageResource(R.mipmap.ic_mic)
                Toast.makeText(mContext, "Mic Enabled", Toast.LENGTH_SHORT).show()
            }
            micEnabled = !micEnabled
            Log.i(TAG, "MIC: $micEnabled")
        }

        // click webcam
        binding.ivWebcam.setOnClickListener { v ->
            if (webcamEnabled) {
                // dang bat thi tat dc neu click vao nut.
                meetingRoom?.disableWebcam()
                binding.ivWebcam.setImageResource(R.mipmap.ic_cam_off)
                Toast.makeText(mContext, "Webcam Disabled", Toast.LENGTH_SHORT).show()
            } else { // dang tat thi bat lai.
                meetingRoom?.enableWebcam()
                binding.ivWebcam.setImageResource(R.mipmap.ic_webcam)
                Toast.makeText(mContext, "Webcam Enabled", Toast.LENGTH_SHORT).show()
            }
            webcamEnabled = !webcamEnabled
            Log.i(TAG, "Camera: $webcamEnabled")
        }


        // leave
        binding.ivLeave.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    mContext, androidx.appcompat.R.anim.abc_fade_in
                )
            )
            meetingRoom?.leave()
        }

    }

    private fun setupMeeting() {
        VideoSDK.config(viewModel.getToken())
        meetingRoom = VideoSDK.initMeeting(
            mContext, meetingID, userName, micEnabled, webcamEnabled, null, null, false, null, null
        )

        meetingRoom?.addEventListener(meetingEventListener)
        meetingRoom?.join()
    }

    private val meetingEventListener: MeetingEventListener = object : MeetingEventListener() {
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
                mContext, participant.displayName + " joined", Toast.LENGTH_SHORT
            ).show()
        }

        override fun onParticipantLeft(participant: Participant) {
            Toast.makeText(
                mContext, participant.displayName + " left", Toast.LENGTH_SHORT
            ).show()
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