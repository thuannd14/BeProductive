package com.ndt.beproductive.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class M003BreakTimeVM : BaseViewModel(), CoroutineScope {
    companion object {
        val TAG: String = M003BreakTimeVM::class.java.name
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

    private var minutesBreak: Int = 5
    private var secondsBreak: Int = 60


    fun startCountDownBreak(): Int {
        launch(Dispatchers.IO) {
            for (i in minutesBreak downTo 0) {
                for (j in secondsBreak downTo 0) {
                    if (job.isCancelled) break
                    Thread.sleep(1000)
                    launch(Dispatchers.Main) {
                        if (j >= 0) {
                            secondTimeBreak.postValue(j)
                        }
                    }
                    if (i >= 0) {
                        minuteTimeBreak.postValue(i)
                    }
                }

            }
        }
        return 0
    }

    fun getCombinedTimeBreak(): LiveData<Pair<Int, Int>> {
        // live data cua minute
        mediatorBreak.addSource(getMinuteBreak()) { minutesBreak ->
            val secondBreak = getSecondBreak().value
            if (secondBreak != null) {
                mediatorBreak.value = Pair(minutesBreak, secondBreak)
            }
        }

        // live data cua second.
        mediatorBreak.addSource(getSecondBreak()) { secondBreak ->
            val minuteBreak = getMinuteBreak().value
            if (minuteBreak != null) {
                mediatorBreak.value = Pair(minutesBreak, secondBreak)
            }
        }
        return mediatorBreak
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // huy bo job.
    }
}