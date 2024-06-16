package com.ndt.beproductive.fragment

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.databinding.M006LoginFragBinding
import com.ndt.beproductive.viewmodel.M006LoginVM

class M006LoginFrag : BaseFrag<M006LoginFragBinding, M006LoginVM>() {
    companion object {
        val TAG: String = M006LoginFrag::class.java.name
    }

    private var isFlag = true

    override fun initViews() {

        binding.btLogin.setOnClickListener {
            checkAccount()
            if (isFlag) {
                mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, false)
            } else {
                Toast.makeText(mContext, "Please Enter Valid Information", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun checkAccount(): Boolean {
        val emailED = binding.edEmailLogin.text.toString().trim()
        val passwordED = binding.edPassLogin.text.toString().trim()
        val email = CommonUtils.getPref(EMAIL)
        val password = CommonUtils.getPref(PASSWORD)

        if (TextUtils.isEmpty(emailED) || TextUtils.isEmpty(passwordED)) {
            isFlag = false
        }
        if (emailED != email || passwordED != password) {
            isFlag = false
        }

        return isFlag

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