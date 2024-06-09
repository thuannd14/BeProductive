package com.ndt.beproductive.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ndt.beproductive.App
import com.ndt.beproductive.ExitDialog
import com.ndt.beproductive.OnDialogCallBack
import com.ndt.beproductive.databinding.M003BreakTimeFragBinding
import com.ndt.beproductive.viewmodel.M003BreakTimeVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class M003BreakTimeFrag : BaseFrag<M003BreakTimeFragBinding, M003BreakTimeVM>(), CoroutineScope {
    companion object {
        val TAG: String = M003BreakTimeFrag::class.java.name
    }

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    private var indexCur = App.instance.getStorage().indexImg
    private var getMinuteFocus: Int = 0
    private var getSecondFocus: Int = 0

    override fun initViews() {
        // set background.
        binding.clMainBreakTime.setBackgroundResource(viewModel.ARR_IMG[indexCur!!])

        // xu li nut exit. Neu đồng ý exit thì về m003MainScreen.Nếu ko vẫn tiếp tục đếm.
        binding.btExitBreak.setOnClickListener {
            handleExit()
        }

        startBreak()
    }

    private fun handleExit() {
        val exitDialog = ExitDialog(mContext, object : OnDialogCallBack {
            override fun callBack(key: String?, data: Any?) {
                if (key == ExitDialog.KEY_YES) {
                    job.cancel()
                    mCallBack.showFrag(M003MainFocusFrag.TAG, null, false)
                } else if (key == ExitDialog.KEY_NO) {
                    // ko can lam gi.
                }
            }
        })

        exitDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun startBreak() {
        viewModel.startCountDownBreak{
            launch(Dispatchers.Main) {
                mCallBack.showFrag(M003StartTimeFrag.TAG, null, false)
            }
        }
        viewModel.getCombinedTimeBreak().observe(viewLifecycleOwner, Observer { pair ->
            val (value1, value2) = pair
            getMinuteFocus = value1
            getSecondFocus = value2
            Log.i(TAG, "Minute: $getMinuteFocus")
            Log.i(TAG, "Second: $getSecondFocus")
            binding.tvBreakTimeCountDown.text = "$getMinuteFocus:$getSecondFocus"
        })
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