package com.example.malllist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GoodsAdapter(private val goodsList: List<Goods>) :
    RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return GoodsViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        val goods = goodsList[position]
        holder.ivImg.setImageResource(goods.imageRes)
        holder.tvName.text = goods.name
        holder.tvDescription.text = goods.description
    }

    override fun getItemCount(): Int = goodsList.size

    class GoodsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val ivImg: ImageView = view.findViewById(R.id.iv_img)
        val tvDescription: TextView = view.findViewById(R.id.tv_description)
    }
}
