package com.example.headlineapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val newsList: List<NewsBean>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_SINGLE) {
            val view = inflater.inflate(R.layout.item_one, parent, false)
            SingleViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_two, parent, false)
            MultiViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int = newsList[position].type

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bean = newsList[position]
        if (holder is SingleViewHolder) {
            holder.tvTop.isVisible = bean.isTop
            holder.ivImg.isVisible = bean.images.isNotEmpty()
            if (bean.images.isNotEmpty()) {
                holder.ivImg.setImageResource(bean.images.first())
            }
            holder.tvTitle.text = bean.title
            holder.tvSource.text = bean.source
            holder.tvComment.text = bean.comment
            holder.tvTime.text = bean.time
        } else if (holder is MultiViewHolder) {
            holder.tvTitle.text = bean.title
            holder.tvSource.text = bean.source
            holder.tvComment.text = bean.comment
            holder.tvTime.text = bean.time
            holder.ivImg1.setImageResource(bean.images.getOrElse(0) { 0 })
            holder.ivImg2.setImageResource(bean.images.getOrElse(1) { 0 })
            holder.ivImg3.setImageResource(bean.images.getOrElse(2) { 0 })
        }
    }

    override fun getItemCount(): Int = newsList.size

    class SingleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTop: TextView = view.findViewById(R.id.tv_top)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvSource: TextView = view.findViewById(R.id.tv_source)
        val tvComment: TextView = view.findViewById(R.id.tv_comment)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val ivImg: ImageView = view.findViewById(R.id.iv_img)
    }

    class MultiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvSource: TextView = view.findViewById(R.id.tv_source)
        val tvComment: TextView = view.findViewById(R.id.tv_comment)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val ivImg1: ImageView = view.findViewById(R.id.iv_img1)
        val ivImg2: ImageView = view.findViewById(R.id.iv_img2)
        val ivImg3: ImageView = view.findViewById(R.id.iv_img3)
    }

    companion object {
        const val TYPE_SINGLE = 1
        const val TYPE_MULTI = 2
    }
}
