package com.su.base.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.su.base.R
import com.su.base.rvlistener.EndlessRecyclerOnScrollListener
import org.jetbrains.anko.find

/**
 * @项目名称：FrameProject
 * @包名：com.su.base.activity
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 9:50
 * @修改人：
 * @修改时间：
 * @类描述: activity基础类，带有基础标题栏，可动态设置标题，提供一个默认列表布局可下拉刷新，也可根据需要自定义其他布局
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun isUseRecycleView(): Boolean
    abstract fun isUseRefreshView(): Boolean
    abstract fun titleId(): Int
    abstract fun customViewId(): Int
    abstract fun onRequest(page: Int)
    abstract fun onRefresh(page: Int)
    abstract fun onLoadMore(page: Int)

    open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(this)

    private var page = 0


    val toolBar: Toolbar by lazy {
        find<Toolbar>(R.id.base_toolbar)
    }

    val containerLayout: FrameLayout by lazy {
        find<FrameLayout>(R.id.base_container)
    }

    var recyclerView: RecyclerView? = null
    var refreshView: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        setSupportActionBar(toolBar)

        if (titleId() != 0) {
            toolBar.setTitle(titleId())
        }

        toolBar.setNavigationIcon(R.drawable.back_arrow)
        toolBar.setNavigationOnClickListener {
            finish()
        }

        val layoutInflater: LayoutInflater = LayoutInflater.from(this)
        if (customViewId() != 0) containerLayout.addView(layoutInflater.inflate(customViewId(), null)) else {
            if (isUseRecycleView()) {
                containerLayout.addView(layoutInflater.inflate(R.layout.layout_recyclerview, null))

                recyclerView = find<RecyclerView>(R.id.base_recyclerView)
                refreshView = find<SwipeRefreshLayout>(R.id.base_refreshView)

                recyclerView!!.layoutManager = getLayoutManager()

                if (getLayoutManager() is LinearLayoutManager) {
                    recyclerView!!.addOnScrollListener(object : EndlessRecyclerOnScrollListener(getLayoutManager() as LinearLayoutManager) {
                        override fun onLoadData() {
                            onLoadMore(page++)
                        }
                    })
                }

                refreshView!!.isEnabled = isUseRefreshView()

                refreshView!!.setOnRefreshListener {
                    onRefresh(page = 0)
                    refreshView!!.isRefreshing = false
                }

            }
        }

        onRequest(page = 0)

    }


}
