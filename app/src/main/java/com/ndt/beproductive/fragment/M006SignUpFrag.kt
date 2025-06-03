package com.ndt.beproductive.fragment

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.ndt.beproductive.App
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.R
import com.ndt.beproductive.databinding.M006SignUpFragBinding
import com.ndt.beproductive.viewmodel.M006SignUpVM

class M006SignUpFrag : BaseFrag<M006SignUpFragBinding, M006SignUpVM>() {

    companion object {
        val TAG: String = M006SignUpFrag::class.java.name
    }

    private var isPasswordVisible = false
    private var isRePasswordVisible = false

    override fun initViews() {
        // Toggle mật khẩu
        binding.ivShowPassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility(binding.edPassReg, binding.ivShowPassword, isPasswordVisible)
        }

        binding.ivEyeRepassword.setOnClickListener {
            isRePasswordVisible = !isRePasswordVisible
            togglePasswordVisibility(
                binding.edRePassReg,
                binding.ivEyeRepassword,
                isRePasswordVisible
            )
        }

        binding.btnSignup.setOnClickListener {
            if (signUp()) {
                mCallBack.showFrag(M001OnBoarding1Frag.TAG, null, false)
            }
        }

        binding.btnFacebook.setOnClickListener {
            mCallBack.showFrag(M006LoginFrag.TAG, null, false)
        }

        binding.btnGoogle.setOnClickListener {
            mCallBack.showFrag(M006LoginFrag.TAG, null, false)
        }
    }

    private fun togglePasswordVisibility(
        field: android.widget.EditText,
        eyeIcon: android.widget.ImageView,
        isVisible: Boolean
    ) {
        field.inputType = if (isVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        field.setSelection(field.text.length)
        eyeIcon.setImageResource(if (isVisible) R.drawable.ic_hide else R.drawable.ic_show)
    }

    private fun signUp(): Boolean {
        val userName = binding.edUsernameReg.text.toString().trim()
        val password = binding.edPassReg.text.toString().trim()
        val rePassword = binding.edRePassReg.text.toString().trim()

        if (userName.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(mContext, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(mContext, "Password must be at least 6 characters", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (password != rePassword) {
            Toast.makeText(mContext, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        val db = App.instance.dbUser()
        if (db.isUsernameExists(userName)) {
            Toast.makeText(mContext, "Username already exists", Toast.LENGTH_SHORT).show()
            return false
        }

        val inserted = db.insertUser(userName, password)
        return if (inserted) {
            Toast.makeText(mContext, "Sign Up Successful", Toast.LENGTH_SHORT).show()
            CommonUtils.savePref(USER_NAME, userName)
            CommonUtils.savePref(PASSWORD, password)
            true
        } else {
            Toast.makeText(mContext, "Sign Up Failed", Toast.LENGTH_SHORT).show()
            false
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): M006SignUpFragBinding {
        return M006SignUpFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M006SignUpVM> {
        return M006SignUpVM::class.java
    }
}
