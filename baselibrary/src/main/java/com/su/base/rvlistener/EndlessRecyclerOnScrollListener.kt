package com.su.base.rvlistener

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager


/**
 * @项目名称：FrameProject
 * @包名：com.su.base.recycler
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 15:09
 * @修改人：
 * @修改时间：
 * @类描述:
 */
abstract class EndlessRecyclerOnScrollListener(var mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var firstVisibleItem= 0
    private var visibleItemCount= 0
    private var totalItemCount = 0

    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
            onLoadData()
            loading = true
        }
    }

    abstract fun onLoadData()

}