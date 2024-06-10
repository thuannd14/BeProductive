package com.ndt.beproductive.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ndt.beproductive.OnCallbackAPI
import com.ndt.beproductive.OnMainCallBack
import com.ndt.beproductive.viewmodel.BaseViewModel


abstract class BaseFrag<T : ViewBinding, M : BaseViewModel> : Fragment(), View.OnClickListener, OnCallbackAPI {

    companion object {
        val TAG: String = BaseFrag::class.java.name
        val ALL_NOTES = 1
        val FOCUS_TIME = 2
        val EXPLORE = 3
        val SETTING = 4
    }


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
        binding = initViewBinding(inflater, container)
        viewModel = ViewModelProvider(this)[getClassVM()]
        viewModel.setActionCallBack(this)
        Log.i(TAG, "Base Frag")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
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
            // Loi call api loi thi call ve m003.
            //mCallBack.showFrag(M004)
            Toast.makeText(context, "Phien da het han!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Error: " + code + "DaTA " + data, Toast.LENGTH_LONG).show()
        }
    }

    /*
    protected open fun changeMenu(color: Int) {
        if (R.id.iv_notes == M002TakingEmptyFrag.ALL_NOTES) {
            binding.includeMenu.ivNotes.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivNotes.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
            }
        } else if (R.id.iv_explore == M002TakingEmptyFrag.EXPLORE) {
            binding.includeMenu.ivExplore.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivExplore.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
            }
        } else if (R.id.iv_pomodoro == M002TakingEmptyFrag.EXPLORE) {
            binding.includeMenu.ivPomodoro.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivPomodoro.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
            }
        } else if (R.id.iv_setting == M002TakingEmptyFrag.EXPLORE) {
            binding.includeMenu.ivSetting.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivSetting.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
            }
        }
    }

     */
}