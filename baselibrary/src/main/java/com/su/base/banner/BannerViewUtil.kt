package com.su.base.banner

import android.content.Context
import android.widget.ImageView
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.Transformer.DepthPage


/**
 * @项目名称：FrameProject
 * @包名：com.su.base.banner
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 15:31
 * @修改人：
 * @修改时间：
 * @类描述:
 */
class BannerViewUtil(val bannerView: Banner, images: ArrayList<Any>, titles: ArrayList<String>) : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {

    }

    init {
        //设置banner样式
        bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        //设置图片加载器
        bannerView.setImageLoader(this)
        //设置图片集合
        bannerView.setImages(images)
        //设置banner动画效果
        bannerView.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
        if (titles.isNotEmpty()) bannerView.setBannerTitles(titles)
        //设置自动轮播，默认为true
        bannerView.isAutoPlay(true)
        //设置轮播时间
        bannerView.setDelayTime(4000)
        //设置指示器位置（当banner模式中有指示器时）
        bannerView.setIndicatorGravity(BannerConfig.RIGHT)
    }

    fun start() {
        bannerView.start()
    }


}