package com.ndt.beproductive.act

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ndt.beproductive.OnMainCallBack
import com.ndt.beproductive.R
import com.ndt.beproductive.fragment.BaseFrag


abstract class BaseAct<V : ViewBinding, M : ViewModel> : AppCompatActivity(), View.OnClickListener,
    OnMainCallBack {

    companion object {
        val TAG: String = BaseAct::class.java.name
    }

    protected lateinit var binding: V
    protected lateinit var viewModel: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[getClassVM()]
        Log.i(TAG, "BaseAct")
        initViews()
    }

    override fun showFrag(tag: String, data: Any?, isBacked: Boolean) {
        try {
            val clazz = Class.forName(tag)
            Log.i(TAG, "NAME TAG: $tag")
            val ctor = clazz.getConstructor()
            val baseFragment: BaseFrag<*, *> = ctor.newInstance() as BaseFrag<*, *>
            baseFragment.setCallBack(this)
            baseFragment.setData(data)
            val transaction = supportFragmentManager.beginTransaction().replace(
                R.id.fr_main, baseFragment, tag
            )
            if (isBacked) {
                transaction.addToBackStack(null)
            }
            transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    override fun showFrag(tag: String, data: Any?, isBack: Boolean) {
//        try {
//            val clazz = Class.forName(tag)
//            Log.i(TAG, "tag $tag")
//            val ctor = clazz.getConstructor()
//
//            // Tao 1 thuc the moi tu lop fragment.
//            val baseFragment: BaseFrag<*, *> = ctor.newInstance() as BaseFrag<*, *>
//            baseFragment.setCallBack(this)
//            baseFragment.setData(data)
//            val transaction = supportFragmentManager.beginTransaction()
//            if (isBack) {
//                transaction.addToBackStack(null)
//            }
//            transaction.replace(R.id.fr_main, baseFragment, tag).commit()
//        } catch (e: Exception) {
//            Log.i(TAG, "Error: ${e.message}")
//            e.printStackTrace()
//        }
//    }

    abstract fun initViewBinding(): V

    abstract fun initViews()

    abstract fun getClassVM(): Class<M>


    override fun onClick(v: View?) {
        v?.startAnimation(
            AnimationUtils.loadAnimation(
                this, com.google.android.material.R.anim.abc_fade_in
            )
        )
        clickViews(v)
    }

    protected open fun clickViews(v: View?) {
        // dung thi ghi de lai.
    }


    override fun backPrevious() {
        onBackPressed()
    }

    protected open fun showNotify(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    protected open fun showNotify(msgID: Int) {
        Toast.makeText(this, msgID, Toast.LENGTH_LONG).show()
    }
}