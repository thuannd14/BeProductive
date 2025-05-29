package com.ndt.beproductive.fragment

import ShareBottomSheet
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

    private var link: String? = null
    override fun initViews() {
        binding.btnCreate.setOnClickListener {
            AndroidNetworking.post(viewModel.getUrl())
                .addHeaders("Authorization", viewModel.getToken()).build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            val meetingId = response.getString("roomId")
                            binding.edRoom.text = meetingId
                            link = meetingId
                            //mCallBack.showFrag(M008MeetingFrag.TAG, meetingId, false)
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


        binding.btnJoin.setOnClickListener {
            goToMeeting()
        }

        binding.ivBack.setOnClickListener {
            mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, false)
        }
        binding.btnShare.setOnClickListener {
            showShareBottomSheet(link ?: "")
        }
    }

    private fun showShareBottomSheet(link: String) {

        if (link.isNullOrEmpty()) {
            Toast.makeText(mContext, "Không có liên kết để chia sẻ", Toast.LENGTH_SHORT).show()
        } else {
            ShareBottomSheet(link!!).show(childFragmentManager, "ShareBottomSheet")
        }


    }

    private fun goToMeeting() {
        val idMeeting = binding.edRoom.text.toString().trim()
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