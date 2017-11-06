package com.su.base.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.su.base.R
import com.su.base.activity.BaseActivity
import com.su.base.rvlistener.EndlessRecyclerOnScrollListener
import org.jetbrains.anko.find

/**
 * @项目名称：FrameProject
 * @包名：com.su.base.fragment
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 10:48
 * @修改人：
 * @修改时间：
 * @类描述:fragment基础类，提供一个默认列表布局可下拉刷新，也可根据需要自定义其他布局
 */
abstract class BaseFragment : Fragment() {

    abstract fun isUseRecycleView(): Boolean
    abstract fun isUseRefreshView(): Boolean
    abstract fun titleId(): Int
    abstract fun customViewId(): Int
    abstract fun onRequest(page: Int)
    abstract fun onRefresh(page: Int)
    abstract fun onLoadMore(page: Int)

    private var page = 0

    open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(getActivity())

    var activity: BaseActivity? = null

    val containerLayout: FrameLayout by lazy {
        baseView!!.find<FrameLayout>(R.id.base_container)
    }

    var recyclerView: RecyclerView? = null
    var refreshView: SwipeRefreshLayout? = null

    var baseView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseView = inflater!!.inflate(R.layout.fragment_base, container, false)

        if (customViewId() != 0) containerLayout.addView(inflater.inflate(customViewId(), null)) else {
            if (isUseRecycleView()) {
                containerLayout.addView(inflater.inflate(R.layout.layout_recyclerview, null))

                recyclerView = containerLayout.findViewById(R.id.base_recyclerView)
                refreshView = containerLayout.findViewById(R.id.base_refreshView)


                refreshView!!.isEnabled = isUseRefreshView()

                recyclerView!!.layoutManager = getLayoutManager()

                if (getLayoutManager() is LinearLayoutManager) {
                    recyclerView!!.addOnScrollListener(object : EndlessRecyclerOnScrollListener(getLayoutManager() as LinearLayoutManager) {
                        override fun onLoadData() {
                            onLoadMore(page++)
                        }
                    })
                }

                refreshView!!.setOnRefreshListener {
                    onRefresh(page = 0)
                    refreshView!!.isRefreshing = false
                }

            }
        }

        return baseView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as BaseActivity?
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser)
            onRequest(page = 0)
    }

}