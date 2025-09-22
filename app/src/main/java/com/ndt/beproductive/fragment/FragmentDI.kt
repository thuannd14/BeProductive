package com.ndt.beproductive.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ndt.beproductive.databinding.FragmentDiBinding
import com.ndt.beproductive.viewmodel.PostHiltViewModel
import com.ndt.beproductive.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDI : BaseFrag<FragmentDiBinding, PostHiltViewModel>() {
    //val post by lazy { Injector.providePostViewModel() }
    private val post: PostHiltViewModel by viewModels()

    override fun initViews() {
        val posts = post.getPosts()
        binding.tvDI.text = posts.joinToString("\n")
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiBinding {
        return FragmentDiBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<PostHiltViewModel> {
        return PostHiltViewModel::class.java
    }

    companion object {
        val TAG: String = FragmentDI::class.java.name
    }
}