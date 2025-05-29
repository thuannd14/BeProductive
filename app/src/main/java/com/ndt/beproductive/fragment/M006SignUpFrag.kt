package com.ndt.beproductive.fragment

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.ndt.beproductive.App
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.OpenGallery
import com.ndt.beproductive.R
import com.ndt.beproductive.databinding.M006SignUpFragBinding
import com.ndt.beproductive.viewmodel.M006SignUpVM

class M006SignUpFrag : BaseFrag<M006SignUpFragBinding, M006SignUpVM>() {

    companion object {
        val TAG: String = M006SignUpFrag::class.java.name
        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    }

    private var isFlag = true
    private var uri: Uri? = null
    private var isPasswordVisible = false



    override fun initViews() {
        binding.ivShowPassword.setOnClickListener {
            if (isPasswordVisible) {
                // Ẩn mật khẩu
                binding.edPassReg.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ivShowPassword.setImageResource(R.drawable.ic_show) // icon con mắt
            } else {
                // Hiện mật khẩu
                binding.edPassReg.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ivShowPassword.setImageResource(R.drawable.ic_hide) // icon con mắt có gạch
            }
            // Di chuyển con trỏ về cuối dòng
            binding.edPassReg.setSelection(binding.edPassReg.text.length)
            isPasswordVisible = !isPasswordVisible
        }

        binding.ivEyeRepassword.setOnClickListener {
            if (isPasswordVisible) {
                // Ẩn mật khẩu
                binding.edRePassReg.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ivEyeRepassword.setImageResource(R.drawable.ic_show) // icon con mắt
            } else {
                // Hiện mật khẩu
                binding.edRePassReg.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ivEyeRepassword.setImageResource(R.drawable.ic_hide) // icon con mắt có gạch
            }
            // Di chuyển con trỏ về cuối dòng
            binding.edRePassReg.setSelection(binding.edPassReg.text.length)
            isPasswordVisible = !isPasswordVisible
        }
        binding.btnSignup.setOnClickListener {
            signUp()
            if (isFlag) {
                mCallBack.showFrag(M001OnBoarding1Frag.TAG, null, false)
            } else {
                Toast.makeText(mContext, "Please Enter Valid Information", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
        }
        binding.btnFacebook.setOnClickListener {
            mCallBack.showFrag(M006LoginFrag.TAG, null, false)
        }

        binding.btnGoogle.setOnClickListener {
            mCallBack.showFrag(M006LoginFrag.TAG, null, false)
        }
    }

    private fun signUp(): Boolean {
        val password = binding.edPassReg.text.toString().trim()
        val userName = binding.edUsernameReg.text.toString().trim()
        val rePassword = binding.edRePassReg.text.toString().trim()

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                rePassword
            )
        ) {
            isFlag = false
        }

        if (password.length < 6 ) {
            isFlag = false
        }else if(password != rePassword){
            isFlag = false
            Toast.makeText(mContext, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }
        else {
            if (App.instance.dbUser().insertUser(userName, password)) {
                Toast.makeText(mContext, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                isFlag = true
            } else {
                Toast.makeText(mContext, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                isFlag = false
            }

            CommonUtils.savePref(USER_NAME, userName)
            CommonUtils.savePref(PASSWORD, password)

        }

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