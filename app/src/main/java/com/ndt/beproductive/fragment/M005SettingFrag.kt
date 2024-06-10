package com.ndt.beproductive.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ndt.beproductive.databinding.M005ProfileFragBinding
import com.ndt.beproductive.viewmodel.M005SettingVM


class M005SettingFrag : BaseFrag<M005ProfileFragBinding, M005SettingVM>() {
    companion object {
        val TAG: String = M005SettingFrag::class.java.name
        const val URI_PATH = "URI_PATH"
    }

    private lateinit var openGalleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var myUri: Uri? = null

    override fun initViews() {

//        myUri = viewModel.getUri()
//        if(myUri != null){
//            binding.ciUsername.setImageBitmap( MediaStore.Images.Media.getBitmap(
//                requireActivity().contentResolver, myUri
//            ))
//        }

        binding.ciUsername.setOnClickListener {
            if (mContext.checkSelfPermission(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                openGallery()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        setAvatar()

        binding.includeMenu.ivNotes.setOnClickListener {
            mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
        }

        binding.includeMenu.ivPomodoro.setOnClickListener {
            mCallBack.showFrag(M003MainFocusFrag.TAG, null, true)
        }
    }

    private fun setAvatar() {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    openGallery()
                } else {
                    showNotify("Accept the permission?")
                }
            }

        openGalleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val intent = it.data ?: return@registerForActivityResult
                    val uri = intent.data
                    if (uri == null) {
                        showNotify("Uri null")
                    } else {
                        viewModel.saveUri(uri)
                    }

                    try {
                        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(
                            requireActivity().contentResolver, uri
                        )
                        binding.ciUsername.setImageBitmap(bitmap)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }

    private fun openGallery() {
        val intentPicker = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        Log.i(TAG, "Change avatar")
        openGalleryLauncher.launch(Intent.createChooser(intentPicker, "Change avatar"))
    }


    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M005ProfileFragBinding {
        return M005ProfileFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M005SettingVM> {
        return M005SettingVM::class.java
    }
}