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
import com.ndt.beproductive.OnMainCallBack
import com.ndt.beproductive.db.DBNote
import com.ndt.beproductive.viewmodel.BaseViewModel


abstract class BaseFrag<T : ViewBinding, M : BaseViewModel> : Fragment(), View.OnClickListener {

    companion object {
        val TAG: String = BaseFrag::class.java.name
    }


    protected lateinit var binding: T
    protected lateinit var viewModel: M
    protected lateinit var mCallBack: OnMainCallBack
    protected lateinit var mContext: Context
    protected var mdata: Any? = null
    //protected lateinit var myDB: DBNote

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
        //myDB = context?.let { DBNote(it) }!!
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


}