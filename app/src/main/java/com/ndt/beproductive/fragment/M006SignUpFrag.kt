package com.ndt.beproductive.fragment

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.OpenGallery
import com.ndt.beproductive.databinding.M006SignUpFragBinding
import com.ndt.beproductive.viewmodel.M006SignUpVM

class M006SignUpFrag : BaseFrag<M006SignUpFragBinding, M006SignUpVM>() {

    companion object {
        val TAG: String = M006SignUpFrag::class.java.name
        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    }

    private var isFlag = true
    private var uri: Uri? = null
    private var openGalleryImg: OpenGallery? = null


    override fun initViews() {
        binding.ccAvatar.setOnClickListener {
            openGalleryImg?.getImg()
            uri = Uri.parse(CommonUtils.getPref(IMG_PATH))
            Log.i(TAG, "Uri: $uri")
            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver, uri
            )
            binding.ccAvatar.setImageBitmap(bitmap)
        }
        binding.btSignUpReg.setOnClickListener {
            signUp()
            if (isFlag) {
                var uri = Uri.parse(CommonUtils.getPref(IMG_PATH))
                mCallBack.showFrag(M001OnBoarding1Frag.TAG, null, false)
            } else {
                Toast.makeText(mContext, "Please Enter Valid Information", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.tvLogin.setOnClickListener {
            mCallBack.showFrag(M006LoginFrag.TAG, null, false)
        }
    }

    private fun signUp(): Boolean {
        val email = binding.edEmailReg.text.toString().trim()
        val password = binding.edPassReg.text.toString().trim()
        val userName = binding.edUsernameReg.text.toString().trim()
        val rePassword = binding.edRePassReg.text.toString().trim()

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                rePassword
            )
        ) {
            isFlag = false
        }
        if (!email.matches(emailPattern.toRegex())) {
            isFlag = false
        }
        if (password.length < 6) {
            isFlag = false
        }
        if (password != rePassword) {
            isFlag = false
        }
        CommonUtils.savePref(USER_NAME, userName)
        CommonUtils.savePref(EMAIL, email)
        CommonUtils.savePref(PASSWORD, password)
        CommonUtils.savePref(IMG_PATH, uri.toString())

        return isFlag
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M006SignUpFragBinding {
        return M006SignUpFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M006SignUpVM> {
        return M006SignUpVM::class.java
    }
}