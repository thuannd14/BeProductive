package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ndt.beproductive.adapter.PopularAdapter
import com.ndt.beproductive.api.reponse.PopularNews
import com.ndt.beproductive.databinding.M004ExploreFragBinding
import com.ndt.beproductive.viewmodel.M004ExploreVM

class M004ExploreFrag : BaseFrag<M004ExploreFragBinding, M004ExploreVM>() {
    companion object {
        val TAG: String = M004ExploreFrag::class.java.name
    }

    private var adapterPopular: PopularAdapter? = null

    override fun initViews() {
        Log.i(TAG, "initViews m004")
        viewModel.getListArticleAPI()
        Log.i(TAG, "${viewModel.getListArticle().size}")
    }

    override fun apiSuccess(key: String, data: Any?) {
        val article = data as PopularNews

        if (article == null) Log.i(TAG, "Null data")


        viewModel.addToList(article.articlesList)
        Log.i(TAG, "Author: ${article.articlesList?.get(0)?.title}")

        Log.i(TAG, "List result:${viewModel.getListArticle().size}")
        if (adapterPopular == null) {
            adapterPopular = PopularAdapter(mContext, viewModel.getListArticle())
            // xu li khi click vao 1 item
            adapterPopular?.getArticle()?.observe(this, Observer<PopularNews.Articles?> { result ->
                if (result == null) return@Observer
                openDetail(result)
            })
            binding.vpPopular.adapter = adapterPopular
        }
        // xu li khi adapter da co du lieu.
        else {
            adapterPopular?.updateArticleList(viewModel.getListArticle())
        }
    }

    private fun openDetail(result: PopularNews.Articles) {
        Log.i(TAG, "${result.author}")
        mCallBack.showFrag(M004DetailPopularFrag.TAG, result, true)
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M004ExploreFragBinding {
        return M004ExploreFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M004ExploreVM> {
        return M004ExploreVM::class.java
    }
}