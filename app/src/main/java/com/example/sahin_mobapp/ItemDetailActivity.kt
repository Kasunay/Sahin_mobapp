package com.example.sahin_mobapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val itemId = intent.getIntExtra("ITEM_ID", -1)
        val itemName = intent.getStringExtra("ITEM_NAME")
        val itemImageResId = intent.getIntExtra("ITEM_IMAGE_RES", -1)

        val detailTextView: TextView = findViewById(R.id.detailTextView)
        val detailImageView: ImageView = findViewById(R.id.detailImageView)

        detailTextView.text = "ID: $itemId\nName: $itemName"

        if (itemImageResId != -1) {
            detailImageView.setImageResource(itemImageResId)
        }
    }
}
