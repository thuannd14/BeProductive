package com.ndt.beproductive.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ndt.beproductive.api.reponse.TopHeadlines
import com.ndt.beproductive.databinding.ItemTopHeadlinesDetailBinding
import com.ndt.beproductive.viewmodel.M004DetailTopHeadlineVM

class M004DetailTopHeadlinesFrag :
    BaseFrag<ItemTopHeadlinesDetailBinding, M004DetailTopHeadlineVM>() {
    companion object {
        val TAG: String = M004DetailTopHeadlinesFrag::class.java.name
    }

    override fun initViews() {
        val article = mdata as TopHeadlines.Articles
        binding.tvTitleTopHeadline.text = article.title
        binding.tvDescTopHeadline.text = article.descArticle
        Glide.with(mContext).load(article.imgPath).into(binding.ivTopHeadline)
        binding.ivBackTopHeadline.setOnClickListener {
            mCallBack.backPrevious()
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): ItemTopHeadlinesDetailBinding {
        return ItemTopHeadlinesDetailBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M004DetailTopHeadlineVM> {
        return M004DetailTopHeadlineVM::class.java
    }
}