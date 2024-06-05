package com.ndt.beproductive.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ndt.beproductive.App
import com.ndt.beproductive.databinding.M003BreakTimeFragBinding
import com.ndt.beproductive.viewmodel.M003BreakTimeVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class M003BreakTimeFrag : BaseFrag<M003BreakTimeFragBinding, M003BreakTimeVM>(), CoroutineScope {
    companion object {
        val TAG: String = M003BreakTimeFrag::class.java.name
    }

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private var indexCur = App.instance.getStorage().indexImg
    private lateinit var contentText: String
    private var getMinuteFocus: Int = 0
    private var getSecondFocus: Int = 0

    override fun initViews() {
        // set background.
        binding.clMainBreakTime.setBackgroundResource(viewModel.ARR_IMG[indexCur!!])

        // xu li nut exit. Neu đồng ý exit thì về m003MainScreen.Nếu ko vẫn tiếp tục đếm.
        binding.btExitBreak.setOnClickListener {
            handleExit()
        }

        // vao thoi gian nghi.
        startBreak()
        launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                startBreak()
            }
            withContext(Dispatchers.IO) {
                mCallBack.showFrag(M003StartTimeFrag.TAG, null, true)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun startBreak() {
        viewModel.startCountDownBreak()
        viewModel.getCombinedTimeBreak().observe(viewLifecycleOwner, Observer { pair ->
            val (value1, value2) = pair
            getMinuteFocus = value1
            getSecondFocus = value2
            Log.i(TAG, "Minute: $getMinuteFocus")
            Log.i(TAG, "Second: $getSecondFocus")
            binding.tvBreakTimeCountDown.text = "$getMinuteFocus:$getSecondFocus"
        })
    }

    private fun handleExit() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
        builder.setTitle("Back to main screen?")
        builder.setMessage("Are you sure?")
        builder.setPositiveButton("Yes, back", DialogInterface.OnClickListener {
            // tat coroutine di va back ve m003.
                dialog, which ->
            mCallBack.showFrag(M003MainFocusFrag.TAG, null, false)
        })

        builder.setNegativeButton("No, stay", DialogInterface.OnClickListener { dialog, which ->
            mCallBack.showFrag(M003MainFocusFrag.TAG, null, false)
        })

        builder.create().show()
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M003BreakTimeFragBinding {
        return M003BreakTimeFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M003BreakTimeVM> {
        return M003BreakTimeVM::class.java
    }
}