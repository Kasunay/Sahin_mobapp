package com.example.sahin_mobapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private var itemList: List<Recipe>,
    private val onItemClicked: (Recipe) -> Unit,
    private val onLikeClicked: (Recipe) -> Unit,
    private val onShareClicked: (Recipe) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    fun updateItems(newItems: List<Recipe>) {
        itemList = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(itemList[position], onItemClicked, onLikeClicked, onShareClicked)
    }

    override fun getItemCount(): Int = itemList.size
}
