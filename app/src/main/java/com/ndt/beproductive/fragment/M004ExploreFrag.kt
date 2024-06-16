package com.ndt.beproductive.fragment

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.ndt.beproductive.App
import com.ndt.beproductive.HorizontalMarginItemDecoration
import com.ndt.beproductive.R
import com.ndt.beproductive.act.MainActivity
import com.ndt.beproductive.adapter.PopularAdapter
import com.ndt.beproductive.adapter.TopHeadlinesAdapter
import com.ndt.beproductive.api.reponse.PopularNews
import com.ndt.beproductive.api.reponse.TopHeadlines
import com.ndt.beproductive.databinding.M004ExploreFragBinding
import com.ndt.beproductive.viewmodel.M004ExploreVM

class M004ExploreFrag : BaseFrag<M004ExploreFragBinding, M004ExploreVM>() {
    companion object {
        val TAG: String = M004ExploreFrag::class.java.name
    }

    private var adapterPopular: PopularAdapter? = null
    private var adapterTopHeadlines: TopHeadlinesAdapter? = null

    override fun initViews() {
        Log.i(TAG, "initViews m004")
        viewModel.getListArticleAPI()
        setupCarousel()
        changMenu()
    }

    private fun changMenu() {
        binding.includeMenu.ivNotes.setOnClickListener(this)
        binding.includeMenu.ivPomodoro.setOnClickListener(this)
        binding.includeMenu.ivExplore.setOnClickListener(this)
        binding.includeMenu.ivSetting.setOnClickListener(this)
        binding.includeMenu.ivData.setOnClickListener(this)

        changeColor(MainActivity.EXPLORE, colorBlue, colorBlack)
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
            mCallBack.showFrag(TAG, null, true)
        } else if (v?.id == R.id.iv_setting) {
            changeColor(MainActivity.SETTING, colorBlue, colorBlack)
            mCallBack.showFrag(M005SettingFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_data) {
            changeColor(MainActivity.DATA, colorBlue, colorBlack)
            mCallBack.showFrag(M007AnalyticsFrag.TAG, null, true)
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
        binding.includeMenu.ivData.setColorFilter(alphaData, PorterDuff.Mode.SRC_IN)

    }

    private fun setAdapterTopHeadlines() {
        val topHeadlines = App.instance.getStorage().topHeadline
        viewModel.addToHeadlines(topHeadlines?.articlesList)
        Log.i(TAG, "Size top headline: ${viewModel.getListTopHeadlines().size}")

        if (adapterTopHeadlines == null) {
            adapterTopHeadlines = TopHeadlinesAdapter(mContext, viewModel.getListTopHeadlines())
            // xu li khi click vao 1 item
            adapterTopHeadlines?.getTopHeadline()
                ?.observe(this, Observer<TopHeadlines.Articles?> { result ->
                    if (result == null) return@Observer
                    openDetailTopHeadlines(result)
                })
            binding.vpNewArticles.adapter = adapterTopHeadlines
        }
        // xu li khi adapter da co du lieu.
        else {
            adapterTopHeadlines?.updateArticleList(viewModel.getListTopHeadlines())
        }
    }

    private fun openDetailTopHeadlines(result: TopHeadlines.Articles) {
        mCallBack.showFrag(M004DetailTopHeadlinesFrag.TAG, result, true)
    }

    override fun apiSuccess(key: String, data: Any?) {
        // de ke call api top headlines ko thi loi ko load duoc.
        viewModel.getLisTopHeadlinesAPI()


        val article = data as PopularNews

        if (article == null) Log.i(TAG, "Null data")


        viewModel.addToList(article.articlesList)
        Log.i(TAG, "Author: ${article.articlesList?.get(0)?.title}")

        Log.i(TAG, "List result:${viewModel.getListArticle().size}")
        if (adapterPopular == null) {
            Log.i(TAG, "Size: ${App.instance.getStorage().articleList?.size}")
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

    private fun setupCarousel() {
        setAdapterTopHeadlines()

        binding.vpPopular.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationY = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationY = -pageTranslationY * position
            page.scaleX = 1 - (0.1f * kotlin.math.abs(position))
            page.alpha = 0.1f + (1 - kotlin.math.abs(position))
        }
        binding.vpPopular.setPageTransformer(pageTransformer)

        // ko co HorizontalMarginItemDecoration cac muc se chong cheo len nhau.
        val itemDecoration = HorizontalMarginItemDecoration(
            mContext, R.dimen.viewpager_current_item_horizontal_margin_2
        )
        binding.vpPopular.addItemDecoration(itemDecoration)
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