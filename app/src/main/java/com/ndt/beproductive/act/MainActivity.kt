package com.ndt.beproductive.act

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.R
import com.ndt.beproductive.databinding.ActivityMainBinding
import com.ndt.beproductive.fragment.M00SplashFrag
import com.ndt.beproductive.viewmodel.CommonVM
import java.io.IOException


class MainActivity : BaseAct<ActivityMainBinding, CommonVM>() {

    companion object {

        val TAG: String = MainActivity::class.java.name
        const val ALL_NOTES = 1
        const val FOCUS_TIME = 2
        const val EXPLORE = 3
        const val SETTING = 4
        const val DATA = 5
        const val SELECT_IMAGE = 101
        const val IMG_PATH = "IMG_PATH"
    }

    private var uriImg: Uri? = null

    override fun getImg() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriImg = data.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver, uriImg
                       )
                        CommonUtils.savePref(IMG_PATH, uriImg.toString())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun initViews() {
        Log.i(TAG, "Main act")
        showFrag(M00SplashFrag.TAG, null, false)
        getImg()
//        binding.includeMenu.ivNotes.setOnClickListener(this)
//        binding.includeMenu.ivPomodoro.setOnClickListener(this)
//        binding.includeMenu.ivExplore.setOnClickListener(this)
//        binding.includeMenu.ivSetting.setOnClickListener(this)
//
//       changeColor(ALL_NOTES, colorBlue, colorBlack)
    }

//    override fun clickViews(v: View?) {
//        if (v?.id == R.id.iv_notes) {
//            changeColor(ALL_NOTES, colorBlue, colorBlack)
//            showFrag(M002TakingEmptyFrag.TAG, null, true)
//        } else if (v?.id == R.id.iv_pomodoro) {
//            changeColor(FOCUS_TIME, colorBlue, colorBlack)
//            showFrag(M003MainFocusFrag.TAG, null, true)
//        } else if (v?.id == R.id.iv_explore) {
//            changeColor(EXPLORE, colorBlue, colorBlack)
//            showFrag(M004ExploreFrag.TAG, null, true)
//        } else if (v?.id == R.id.iv_setting) {
//            changeColor(SETTING, colorBlue, colorBlack)
//            showFrag(M005SettingFrag.TAG, null, true)
//        }
//    }

//    private fun changeColor(key: Int, colorBlue: Int, colorBlack: Int) {
//        val alphaNote = if (key == ALL_NOTES) colorBlue else colorBlack
//        binding.includeMenu.ivNotes.setColorFilter(alphaNote, PorterDuff.Mode.SRC_IN)
//
//        val alphaLFocus = if (key == FOCUS_TIME) colorBlue else colorBlack
//        binding.includeMenu.ivPomodoro.setColorFilter(alphaLFocus, PorterDuff.Mode.SRC_IN)
//
//        val alphaExplore = if (key == EXPLORE) colorBlue else colorBlack
//        binding.includeMenu.ivExplore.setColorFilter(alphaExplore, PorterDuff.Mode.SRC_IN)
//
//        val alphaSetting = if (key == SETTING) colorBlue else colorBlack
//        binding.includeMenu.ivSetting.setColorFilter(alphaSetting, PorterDuff.Mode.SRC_IN)
//    }


    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun callBack(key: String?, data: Any?) {
        // ko ghi de.
    }

}