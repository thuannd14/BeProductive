package com.ndt.beproductive.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ndt.beproductive.OnCallbackAPI
import com.ndt.beproductive.OnMainCallBack
import com.ndt.beproductive.R
import com.ndt.beproductive.viewmodel.BaseViewModel


abstract class BaseFrag<T : ViewBinding, M : BaseViewModel> : Fragment(), View.OnClickListener,
    OnCallbackAPI {

    companion object {
        val TAG: String = BaseFrag::class.java.name

        const val IMG_PATH = "IMG_PATH"
        const val USER_NAME = "USER_NAME"
        const val EMAIL = "EMAIL"
        const val PASSWORD = "PASS_WORD"
        const val PERMISSION_REQ_ID = 22
        const val PERMISSION_REQ_READ_GALLERY = 23
    }

    protected var colorBlue = 0
    protected var colorBlack = 0


    protected lateinit var binding: T
    protected lateinit var viewModel: M
    protected lateinit var mCallBack: OnMainCallBack
    protected lateinit var mContext: Context
    protected var mdata: Any? = null

    open fun setCallBack(callBack: OnMainCallBack) {
        this.mCallBack = callBack
    }

    open fun setData(data: Any?) {
        this.mdata = data
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        colorBlue = ContextCompat.getColor(mContext, R.color.light_blue)
        colorBlack = ContextCompat.getColor(mContext, R.color.black)
        binding = initViewBinding(inflater, container)
        viewModel = ViewModelProvider(this)[getClassVM()]
        viewModel.setActionCallBack(this)
        Log.i(TAG, "Base Frag")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkSelfPermission(
            REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID
        )
        checkSelfPermission(
            REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID
        )
        checkSelfPermission(
            REQUESTED_PERMISSIONS[2], PERMISSION_REQ_READ_GALLERY
        )
        checkSelfPermission(
            REQUESTED_PERMISSIONS[3], PERMISSION_REQ_READ_GALLERY
        )
        checkSelfPermission(
            REQUESTED_PERMISSIONS[4], PERMISSION_REQ_READ_GALLERY
        )
        initViews()
    }

    private val REQUESTED_PERMISSIONS = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET
    )

    protected open fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(
                mContext, permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, REQUESTED_PERMISSIONS, requestCode
                )
            }
            return false
        }
        return true
    }


    protected abstract fun initViews()

    protected abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    protected abstract fun getClassVM(): Class<M>

    override fun onClick(v: View?) {
        v?.startAnimation(
            AnimationUtils.loadAnimation(
                context, com.google.android.material.R.anim.abc_fade_in
            )
        )
        clickViews(v)
    }

    protected open fun clickViews(v: View?) {
    }


    protected open fun showNotify(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    protected open fun showNotify(msgID: Int) {
        Toast.makeText(context, msgID, Toast.LENGTH_LONG).show()
    }

    override fun apiSuccess(key: String, data: Any?) {
        // lam gi day neu thanh cong.
    }

    override fun apiError(key: String, code: Int, data: Any) {
        if (code == 401) {
            mCallBack.showFrag(M004ExploreFrag.TAG, null, false)
            Toast.makeText(context, "Phien da het han!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Error: " + code + "DaTA " + data, Toast.LENGTH_LONG).show()
        }
    }

}