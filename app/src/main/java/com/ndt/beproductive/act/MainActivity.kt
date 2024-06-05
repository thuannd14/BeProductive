package com.ndt.beproductive.act

import android.util.Log
import com.ndt.beproductive.databinding.ActivityMainBinding
import com.ndt.beproductive.fragment.M002CreateNoteFrag
import com.ndt.beproductive.fragment.M003ChangeThemeFrag
import com.ndt.beproductive.viewmodel.CommonVM

class MainActivity : BaseAct<ActivityMainBinding, CommonVM>() {

    companion object {
        val TAG: String = MainActivity::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "Main act")
        showFrag(M002CreateNoteFrag.TAG, null, false)
    }

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun callBack(key: String?, data: Any?) {
        // ko ghi de.
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_CODE) {
//            openGallery()
//        } else {
//            Toast.makeText(this, "Accept the permission?", Toast.LENGTH_SHORT).show()
//        }
//    }

//    fun openGallery() {
//        Log.i(TAG, "openGallery")
//        val intentPicker = Intent(Intent.ACTION_PICK)
//        intentPicker.type = "image/*"
//        intentPicker.action = Intent.ACTION_GET_CONTENT
//        launcher.launch(Intent.createChooser(intentPicker, "Change avatar"))
//    }


//    private val launcher =
//        registerForActivityResult<Intent, ActivityResult>(ActivityResultContracts.StartActivityForResult(),
//            object : ActivityResultCallback<ActivityResult?> {
//                override fun onActivityResult(result: ActivityResult?) {
//                    if (result?.resultCode == RESULT_OK) {
//                        val intent = result.data ?: return
//                        val uri = intent.data
//                        if (uri != null) {
//                            settingFrag?.setUri(uri)
//                        }
//                        try {
//                            val bitmap = MediaStore.Images.Media.getBitmap(
//                                contentResolver, uri
//                            )
//                            settingFrag?.setBitMapIv(bitmap)
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
//                }
//            })
}