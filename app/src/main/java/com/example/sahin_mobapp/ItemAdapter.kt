package com.example.sahin_mobapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private var itemList: List<Item>,
    private val onItemClicked: (Item) -> Unit,
    private val onLikeClicked: (Item) -> Unit,
    private val onShareClicked: (Item) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(itemList[position], onItemClicked, onLikeClicked, onShareClicked)
    }

    override fun getItemCount(): Int = itemList.size
}
