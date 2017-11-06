package com.su.frameproject

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.su.base.adapter.BaseAdapter
import com.tuyenmonkey.mkloader.MKLoader
import org.jetbrains.anko.dip
import org.jetbrains.anko.find

/**
 * @项目名称：FrameProject
 * @包名：com.su.frameproject
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 14:36
 * @修改人：
 * @修改时间：
 * @类描述:
 */
class MyAdapter(contentDates: List<Any>) : BaseAdapter(contentDates, false, false) {
    override fun topLayoutId(): Int = 0

    override fun topViewHolder(itemView: View?): RecyclerView.ViewHolder = TopViewHolder(itemView)

    override fun contentLayoutId(): Int = R.layout.adapter

    override fun contentViewHolder(itemView: View?): RecyclerView.ViewHolder = ContentViewHolder(itemView)

    val tvParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
    val lineParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)


    override fun setTopViewData(holder: RecyclerView.ViewHolder) {
    }

    override fun setItemData(holder: RecyclerView.ViewHolder) {

        val position: Int = holder.itemView.getTag(R.id.position) as Int

        if (holder is ContentViewHolder) {
            if ((position + 1) % 4 == 0) {
                holder.line1.visibility = View.GONE
                holder.line2.visibility = View.VISIBLE
            } else {
                holder.line1.visibility = View.VISIBLE
                holder.line2.visibility = View.GONE
            }

            holder.tv01.text = ((position+1).toString())
            holder.tv02.text = "37"
            holder.tv03.text = "4"
            holder.tv04.text = "7"
            holder.tv05.text = "9"
            holder.tv06.text = "2"
            holder.tv07.text = "6"
            holder.tv08.text = "8"
            holder.tv09.text = "3"

        }

    }

    private inner class ContentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val llContent: LinearLayout by lazy {
            itemView!!.find<LinearLayout>(R.id.ll_content)
        }

        val line1: View by lazy {
            itemView!!.find<View>(R.id.line1)
        }

        val line2: View by lazy {
            itemView!!.find<View>(R.id.line2)
        }

        val tv01: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_01)
        }

        val tv02: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_02)
        }

        val tv03: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_03)
        }

        val tv04: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_04)
        }

        val tv05: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_05)
        }

        val tv06: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_06)
        }

        val tv07: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_07)
        }

        val tv08: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_08)
        }

        val tv09: TextView by lazy {
            itemView!!.find<TextView>(R.id.tv_09)
        }


    }

    private class TopViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }

}