package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ndt.beproductive.databinding.M008JoinFragBinding
import com.ndt.beproductive.viewmodel.M008JoinVM
import org.json.JSONException
import org.json.JSONObject

class M008JoinFrag : BaseFrag<M008JoinFragBinding, M008JoinVM>() {
    companion object {
        val TAG: String = M008JoinFrag::class.java.name
    }

    override fun initViews() {
        binding.btCreateMeeting.setOnClickListener {
            AndroidNetworking.post(viewModel.getUrl())
                .addHeaders("Authorization", viewModel.getToken()).build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            // response will contain `roomId`
                            val meetingId = response.getString("roomId")
                            binding.tvRoomId.text = meetingId
                            Log.i(TAG, "meetingId:$meetingId")
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onError(anError: ANError) {
                        anError.printStackTrace()
                        Toast.makeText(mContext, anError.message, Toast.LENGTH_SHORT).show()
                    }
                })
        }


        binding.btJoin.setOnClickListener {
            goToMeeting()
        }


    }

    private fun goToMeeting() {
        val idMeeting = binding.etMeetingId.text.toString().trim()
        mCallBack.showFrag(M008MeetingFrag.TAG, idMeeting, true)
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M008JoinFragBinding {
        return M008JoinFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M008JoinVM> {
        return M008JoinVM::class.java
    }
}