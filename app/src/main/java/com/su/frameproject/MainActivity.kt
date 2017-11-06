package com.su.frameproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.su.base.activity.BaseActivity
import com.su.base.rvmanager.FullyLinearLayoutManager
import org.jetbrains.anko.find
import android.util.DisplayMetrics
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.LinearLayout


class MainActivity : BaseActivity() {
    override fun onRequest(page: Int) {
    }

    override fun onRefresh(page: Int) {
    }

    override fun onLoadMore(page: Int) {
    }

    override fun isUseRecycleView(): Boolean = false
    override fun isUseRefreshView(): Boolean = false
    override fun titleId(): Int = 0
    override fun customViewId(): Int = R.layout.activity_main

//    val iv1: ImageView by lazy {
//        find<ImageView>(R.id.iv_01)
//    }
//
//    val iv2: ImageView by lazy {
//        find<ImageView>(R.id.iv_02)
//    }
//
//    val iv3: ImageView by lazy {
//        find<ImageView>(R.id.iv_03)
//    }
//
//    val iv4: ImageView by lazy {
//        find<ImageView>(R.id.iv_04)
//    }
//
//    val iv5: ImageView by lazy {
//        find<ImageView>(R.id.iv_05)
//    }
//
//    val iv6: ImageView by lazy {
//        find<ImageView>(R.id.iv_06)
//    }
//
//    val iv7: ImageView by lazy {
//        find<ImageView>(R.id.iv_07)
//    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val metrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(metrics)
//        val width = metrics.widthPixels
//
//        Picasso.with(this).load("http://img0.cri0.fm/M00/09/11/wKjIFlgi2QiAemY-AAAAAAAAAAA872.570x380.jpg").into(iv1)
//        var height: Int = (width * 380 / 570)
//        var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//        iv1.layoutParams = params
//
//        Picasso.with(this).load("http://img1.cri0.fm/M00/56/1C/wKjIFFgi2QiAeXgqAAAAAAAAAAA461.570x422.jpg").into(iv2)
//        height = (width * 422 / 570)
//        params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//        iv2.layoutParams = params
//
//        Picasso.with(this).load("http://img0.cri0.fm/M00/56/1C/wKjIFFgi2QiAHjk7AAAAAAAAAAA242.570x436.jpg").into(iv3)
//        height = (width * 436 / 570)
//        params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//        iv3.layoutParams = params
//
//        Picasso.with(this).load("http://img0.cri0.fm/M00/09/11/wKjIFlgi2QmAPCdBAAAAAAAAAAA022.570x400.jpg").into(iv4)
//        height = (width * 400 / 570)
//        params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//        iv4.layoutParams = params
//
//        Picasso.with(this).load("http://img1.cri0.fm/M00/09/11/wKjIFlgi2QqAAKezAAAAAAAAAAA983.440x534.jpg").into(iv5)
//        height = (width * 534 / 440)
//        params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//        iv5.layoutParams = params
//
//        Picasso.with(this).load("http://img1.cri0.fm/M00/56/1C/wKjIFFgi2QqABGOzAAAAAAAAAAA701.570x435.jpg").into(iv6)
//        height = (width * 435 / 570)
//        params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//        iv6.layoutParams = params
//
//        Picasso.with(this).load("http://img1.cri0.fm/M00/09/11/wKjIFlgi2QuAFnQhAAAAAAAAAAA159.383x493.jpg").into(iv7)
//        height = (width * 493 / 383)
//        params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//        iv7.layoutParams = params

    }


}
