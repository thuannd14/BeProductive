package com.ndt.beproductive.fragment

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.ndt.beproductive.App
import com.ndt.beproductive.R
import com.ndt.beproductive.act.MainActivity
import com.ndt.beproductive.databinding.M007AnalysticTimeBinding
import com.ndt.beproductive.viewmodel.M007AnalyticsVM

class M007AnalyticsFrag : BaseFrag<M007AnalysticTimeBinding, M007AnalyticsVM>() {
    companion object {
        val TAG: String = M007AnalyticsFrag::class.java.name
    }

    private var timeFocus = App.instance.getStorage().totalTimeFocus
    private var timeBreak = App.instance.getStorage().totalTimeBreak
    private lateinit var timeList: MutableList<PieEntry>

    override fun initViews() {
        Log.d(TAG, "Total Time Focus: $timeFocus")
        binding.ivBackData.setOnClickListener {
            mCallBack.backPrevious()
        }
        changMenu()
        setDataChart()
    }

    private fun setDataChart() {

        timeList = mutableListOf()
        timeList.add(PieEntry(timeFocus.toFloat(), "Focus"))
        timeList.add(PieEntry(timeBreak.toFloat(), "Break"))


        val dataSet = PieDataSet(timeList, "")
        dataSet.setColors(*ColorTemplate.JOYFUL_COLORS)


        val data = PieData(dataSet)
        binding.chartDataTime.data = data
        binding.chartDataTime.animateXY(2000, 2000)
    }


    private fun changMenu() {
        binding.includeMenu.ivNotes.setOnClickListener(this)
        binding.includeMenu.ivPomodoro.setOnClickListener(this)
        binding.includeMenu.ivExplore.setOnClickListener(this)
        binding.includeMenu.ivSetting.setOnClickListener(this)
        binding.includeMenu.ivRoom.setOnClickListener(this)

        changeColor(MainActivity.DATA, colorBlue, colorBlack)
    }

    override fun clickViews(v: View?) {
        if (v?.id == R.id.iv_notes) {
            changeColor(MainActivity.ALL_NOTES, colorBlue, colorBlack)
            mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_pomodoro) {
            changeColor(MainActivity.FOCUS_TIME, colorBlue, colorBlack)
            mCallBack.showFrag(M003MainFocusFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_explore) {
            changeColor(MainActivity.EXPLORE, colorBlue, colorBlack)
            mCallBack.showFrag(M004ExploreFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_setting) {
            changeColor(MainActivity.SETTING, colorBlue, colorBlack)
            mCallBack.showFrag(M005SettingFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_room) {
            changeColor(MainActivity.DATA, colorBlue, colorBlack)
            mCallBack.showFrag(TAG, null, true)
        }
    }

    private fun changeColor(key: Int, colorBlue: Int, colorBlack: Int) {
        val alphaNote = if (key == MainActivity.ALL_NOTES) colorBlue else colorBlack
        binding.includeMenu.ivNotes.setColorFilter(alphaNote, PorterDuff.Mode.SRC_IN)

        val alphaLFocus = if (key == MainActivity.FOCUS_TIME) colorBlue else colorBlack
        binding.includeMenu.ivPomodoro.setColorFilter(alphaLFocus, PorterDuff.Mode.SRC_IN)

        val alphaExplore = if (key == MainActivity.EXPLORE) colorBlue else colorBlack
        binding.includeMenu.ivExplore.setColorFilter(alphaExplore, PorterDuff.Mode.SRC_IN)

        val alphaSetting = if (key == MainActivity.SETTING) colorBlue else colorBlack
        binding.includeMenu.ivSetting.setColorFilter(alphaSetting, PorterDuff.Mode.SRC_IN)

        val alphaData = if (key == MainActivity.DATA) colorBlue else colorBlack
        binding.includeMenu.ivRoom.setColorFilter(alphaData, PorterDuff.Mode.SRC_IN)

    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M007AnalysticTimeBinding {
        return M007AnalysticTimeBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M007AnalyticsVM> {
        return M007AnalyticsVM::class.java
    }
}