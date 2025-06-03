package com.ndt.beproductive.fragment

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.ndt.beproductive.App
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.databinding.M006LoginFragBinding
import com.ndt.beproductive.db.DBUser
import com.ndt.beproductive.viewmodel.M006LoginVM

class M006LoginFrag : BaseFrag<M006LoginFragBinding, M006LoginVM>() {
    companion object {
        val TAG: String = M006LoginFrag::class.java.name
    }

    override fun initViews() {

        binding.btLogin.setOnClickListener {
            val username = binding.edEmailLogin.text.toString().trim()
            val password = binding.edPassLogin.text.toString().trim()

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(mContext, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isValid = App.instance.dbUser().isValidUser(username, password)

            if (isValid) {
                // Lưu thông tin đăng nhập vào SharedPreferences
                CommonUtils.savePref(USER_NAME, username)
                CommonUtils.savePref(PASSWORD, username)
                mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, false)
            } else {
                Toast.makeText(mContext, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvForgetPassword.setOnClickListener{
            forgetPassword()
        }

        binding.tvSignUp.setOnClickListener {
            mCallBack.showFrag(M006SignUpFrag.TAG, null, false)
        }


    }

    private fun forgetPassword() {

    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M006LoginFragBinding {
        return M006LoginFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M006LoginVM> {
        return M006LoginVM::class.java
    }
}