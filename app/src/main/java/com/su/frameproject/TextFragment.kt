package com.su.frameproject

import android.util.Log
import com.su.base.fragment.BaseFragment

/**
 * @项目名称：FrameProject
 * @包名：com.su.frameproject
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 11:33
 * @修改人：
 * @修改时间：
 * @类描述:
 */
class TextFragment :BaseFragment(){
    override fun onRequest(page: Int) {
    }

    override fun onRefresh(page: Int) {
    }

    override fun onLoadMore(page: Int) {
    }


    override fun isUseRecycleView(): Boolean  = true
    override fun isUseRefreshView(): Boolean = true
    override fun titleId(): Int  = 0
    override fun customViewId(): Int = R.layout.fragment_text

}