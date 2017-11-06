package com.su.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.su.base.R
import com.su.base.adapter.listener.ItemOnClickListener
import com.tuyenmonkey.mkloader.MKLoader
import org.jetbrains.anko.find

/**
 * @项目名称：FrameProject
 * @包名：com.su.base.adapter
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 14:05
 * @修改人：
 * @修改时间：
 * @类描述:
 */
abstract class BaseAdapter(var contentDates: List<Any>? = null, var hasTopView: Boolean = false, var hasLoadMore: Boolean = true) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TOP_ITEM = topLayoutId()
    private val CONTENT_ITEM = contentLayoutId()
    private val BOTTOM_ITEM = R.layout.layout_list_load_more

    private var itemOnClickListener: ItemOnClickListener? = null

    abstract fun contentLayoutId(): Int
    abstract fun topLayoutId(): Int
    abstract fun contentViewHolder(itemView: View?): RecyclerView.ViewHolder
    abstract fun topViewHolder(itemView: View?): RecyclerView.ViewHolder
    abstract fun setItemData(holder: RecyclerView.ViewHolder)
    abstract fun setTopViewData(holder: RecyclerView.ViewHolder)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            TOP_ITEM -> {
                setTopViewData(holder!!)
            }
            CONTENT_ITEM -> {

                if (hasTopView)
                    holder!!.itemView.setTag(R.id.data, contentDates!![position - 1])
                else
                    holder!!.itemView.setTag(R.id.data, contentDates!![position])

                holder.itemView.setTag(R.id.position, position)
                setItemData(holder)
            }
            BOTTOM_ITEM -> {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
        return if (viewType == TOP_ITEM) {
            topViewHolder(view)
        } else if (viewType == BOTTOM_ITEM) {
            BottomViewHolder(view)
        } else {
            contentViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int =
            when (position) {
                0 -> {
                    if (hasTopView) TOP_ITEM else CONTENT_ITEM
                }
                else -> {
                    if (hasLoadMore and (position == itemCount - 1)) {
                        BOTTOM_ITEM
                    } else {
                        CONTENT_ITEM
                    }
                }
            }

    override fun getItemCount(): Int =
            if (contentDates == null) {
                0
            } else {
                if (hasLoadMore and hasTopView) {
                    contentDates!!.size + 2
                } else if (!hasLoadMore and !hasTopView) {
                    contentDates!!.size
                } else {
                    contentDates!!.size + 1
                }

            }

    fun setItemOnClickListener(itemOnClickListener: ItemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener
    }

    class BottomViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val loadmore: MKLoader by lazy {
            itemView!!.find<MKLoader>(R.id.loadmore)
        }
    }

    fun setLoadAll(isCanLoad: Boolean) {
        hasLoadMore = isCanLoad
    }

}