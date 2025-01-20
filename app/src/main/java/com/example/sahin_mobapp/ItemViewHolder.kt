package com.example.sahin_mobapp

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
    private val itemName: TextView = itemView.findViewById(R.id.itemName)
    private val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)
    private val btnShare: ImageButton = itemView.findViewById(R.id.btnShare)

    fun bindData(
        item: Item,
        onItemClicked: (Item) -> Unit,
        onLikeClicked: (Item) -> Unit,
        onShareClicked: (Item) -> Unit
    ) {
        itemImage.setImageResource(item.imageResource)
        itemName.text = item.name
        itemView.setOnClickListener { onItemClicked(item) }
        btnFavorite.setOnClickListener { onLikeClicked(item) }
        btnShare.setOnClickListener { onShareClicked(item) }
    }
}
