package com.ndt.beproductive.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.ndt.beproductive.App
import com.ndt.beproductive.fragment.M003BreakTimeFrag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class M003BreakTimeVM : BaseViewModel(), CoroutineScope {
    companion object {
        val TAG: String = M003BreakTimeVM::class.java.name
    }


    private var minutesBreak: Int = 0
    private var secondsBreak: Int = 0

    init {
        minutesBreak = 4
        secondsBreak = 60
        App.instance.getStorage().totalTimeBreak += minutesBreak
    }

    // MediatorLiveData để quản lí 2 Livedata.
    private val mediatorBreak = MediatorLiveData<Pair<Int, Int>>()

    private var job: Job = Job()

    private var minuteTimeBreak: MutableLiveData<Int> = MutableLiveData()
    private var secondTimeBreak: MutableLiveData<Int> = MutableLiveData()

    private fun getMinuteBreak(): MutableLiveData<Int> {
        return minuteTimeBreak
    }

    private fun getSecondBreak(): MutableLiveData<Int> {
        return secondTimeBreak
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    fun startCountDownBreak(action: () -> Unit) {
        Log.i(TAG, "startCountDownBreak")
        launch(Dispatchers.IO) {
            for (i in minutesBreak downTo 0) {
                for (j in secondsBreak downTo 0) {
                    if (job.isCancelled) break
                    Thread.sleep(50)
                    launch(Dispatchers.Main) {
                        secondTimeBreak.postValue(j)
                        Log.i(M003BreakTimeFrag.TAG, "post time: $i:$j")
                    }
                    minuteTimeBreak.postValue(i)
                }
            }
            action()
        }
    }

    fun getCombinedTimeBreak(): LiveData<Pair<Int, Int>> {
        // live data cua minute
        mediatorBreak.addSource(getMinuteBreak()) { minuteBreak ->
            val secondBreak = getSecondBreak().value
            if (secondBreak != null) {
                mediatorBreak.value = Pair(minuteBreak, secondBreak)
            }
        }

        // live data cua second.
        mediatorBreak.addSource(getSecondBreak()) { secondBreak ->
            val minuteBreak = getMinuteBreak().value
            if (minuteBreak != null) {
                mediatorBreak.value = Pair(minuteBreak, secondBreak)
            }
        }
        return mediatorBreak
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // huy bo job.
    }
}