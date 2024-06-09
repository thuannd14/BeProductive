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

class M003StartTimeVM : BaseViewModel(), CoroutineScope {
    companion object {
        val TAG: String = M003StartTimeVM::class.java.name
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    // MediatorLiveData để quản lí 2 Livedata.
    private val mediatorFocus = MediatorLiveData<Pair<Int, Int>>()

    private var job: Job = Job()
    private var minuteFocus: MutableLiveData<Int> = MutableLiveData()
    private var secondFocus: MutableLiveData<Int> = MutableLiveData()

    private fun getMinute(): MutableLiveData<Int> {
        return minuteFocus
    }

    private fun getSecond(): MutableLiveData<Int> {
        return secondFocus
    }

    private var minute: Int = 2
    private var second: Int = 60

    fun startCountDownFocus(action: () -> Unit) {
        Log.i(TAG, "startCountDownFocus")
        launch(Dispatchers.IO) {
            for (i in minute downTo 0) {
                for (j in second downTo 0) {
                    if (job.isCancelled) break
                    Thread.sleep(50)
                    launch(Dispatchers.Main) {
                        secondFocus.postValue(j)
                        Log.i(TAG, "post time: $i:$j")
                    }
                    minuteFocus.postValue(i)
                }
            }
            action()
        }
    }

    fun getCombinedTimeFocus(): LiveData<Pair<Int, Int>> {
        // live data cua minute
        mediatorFocus.addSource(getMinute()) { minute ->
            val second = getSecond().value
            if (second != null) {
                mediatorFocus.value = Pair(minute, second)
            }
        }

        // live data cua second.
        mediatorFocus.addSource(getSecond()) { second ->
            val minute = getMinute().value
            if (minute != null) {
                mediatorFocus.value = Pair(minute, second)
            }
        }
        return mediatorFocus
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // huy bo job.
    }
}