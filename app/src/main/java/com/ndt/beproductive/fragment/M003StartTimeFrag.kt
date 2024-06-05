package com.ndt.beproductive.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ndt.beproductive.App
import com.ndt.beproductive.ExitDialog
import com.ndt.beproductive.OnDialogCallBack
import com.ndt.beproductive.databinding.M003StartTimeFragBinding
import com.ndt.beproductive.viewmodel.M003StartTimeVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class M003StartTimeFrag : BaseFrag<M003StartTimeFragBinding, M003StartTimeVM>(), CoroutineScope {
    companion object {
        val TAG: String = M003StartTimeFrag::class.java.name
    }

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    private var indexCur = App.instance.getStorage().indexImg
    private lateinit var contentText: String
    private var getMinuteFocus: Int = 0
    private var getSecondFocus: Int = 0

    override fun initViews() {
        // xu li dem nguoc.
        startTime()

        //Hien thi text.
        showContent()

        // set background.
        binding.clMainStartTime.setBackgroundResource(viewModel.ARR_IMG[indexCur!!])

        // xu li nut exit. Neu đồng ý exit thì về m003MainScreen.Nếu ko vẫn tiếp tục đếm.
        binding.btExit.setOnClickListener {
            //handleExit()
            showExitDialog()
        }

//        launch(Dispatchers.Main) {
//            withContext(Dispatchers.IO) {
//                startTime()
//            }
//            withContext(Dispatchers.IO) {
//                mCallBack.showFrag(M003BreakTimeFrag.TAG, null, true)
//            }
//        }
    }

    private fun showExitDialog() {
        val exitDialog = ExitDialog(mContext, object : OnDialogCallBack {
            override fun callBack(key: String?, data: Any?) {
                if (key == ExitDialog.KEY_YES) {
                    mCallBack.showFrag(M003MainFocusFrag.TAG, null, false)
                } else if (key == ExitDialog.KEY_NO) {
                    // ko can lam gi.
                }
            }
        })
        exitDialog.show()
    }

    private fun showContent() {
        contentText = App.instance.getStorage().contentText!!
        Log.i(TAG, "Content: $contentText")
        binding.tvQuote.text = contentText
    }

    @SuppressLint("SetTextI18n")
    private fun startTime() {

        viewModel.startCountDownFocus()
        viewModel.getCombinedTimeFocus().observe(viewLifecycleOwner, Observer { pair ->
            val (value1, value2) = pair
            getMinuteFocus = value1
            getSecondFocus = value2
            Log.i(TAG, "Minute: $getMinuteFocus")
            Log.i(TAG, "Second: $getSecondFocus")
            binding.tvTimeCountDown.text = "$getMinuteFocus:$getSecondFocus"
        })

//            val rs = viewModel.startCountDownFocus()
//            if (rs == 0) {
//                withContext(Dispatchers.IO) {
//                    mCallBack.showFrag(M003BreakTimeFrag.TAG, null, true)
//                }
//            }
    }

//    private fun handleExit() {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
//        builder.setTitle("Back to main screen?")
//        builder.setMessage("Are you sure?")
//        builder.setPositiveButton("Yes, back", DialogInterface.OnClickListener {
//            // tat coroutine di va back ve m003.
//                dialog, which ->
//            mCallBack.showFrag(M003MainFocusFrag.TAG, null, false)
//        })
//
//        builder.setNegativeButton("No, stay", DialogInterface.OnClickListener { dialog, which ->
//            mCallBack.showFrag(M003MainFocusFrag.TAG, null, false)
//        })
//
//        builder.create().show()
//    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M003StartTimeFragBinding {
        return M003StartTimeFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M003StartTimeVM> {
        return M003StartTimeVM::class.java
    }
}