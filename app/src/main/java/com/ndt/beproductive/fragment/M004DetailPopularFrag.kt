package com.ndt.beproductive.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ndt.beproductive.api.reponse.PopularNews
import com.ndt.beproductive.databinding.ItemSpecificTopicPopularBinding
import com.ndt.beproductive.viewmodel.M004DetailVM

class M004DetailPopularFrag : BaseFrag<ItemSpecificTopicPopularBinding, M004DetailVM>() {
    companion object {
        val TAG: String = M004DetailPopularFrag::class.java.name
    }

    override fun initViews() {
        val article = mdata as PopularNews.Articles
        binding.tvTitlePopular.text = article.title
        binding.tvDescPopular.text = article.descArticle
        Glide.with(mContext).load(article.imgPath).into(binding.ivPopular)
        binding.ivBackPopular.setOnClickListener {
            mCallBack.backPrevious()
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): ItemSpecificTopicPopularBinding {
        return ItemSpecificTopicPopularBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M004DetailVM> {
        return M004DetailVM::class.java
    }
}