package com.example.sahin_mobapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemDetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val itemId = intent.getIntExtra("ITEM_ID", -1) ?: -1
        val itemName = intent.getStringExtra("ITEM_NAME") ?: "Unknown"
        val itemImageRes = intent.getIntExtra("ITEM_IMAGE_RES", -1) ?: -1

        val detailTextView: TextView = findViewById(R.id.detailTextView)
        val detailImageView: ImageView = findViewById(R.id.detailImageView)

        detailTextView.text = "ID: $itemId\nName: $itemName"

        if (itemImageRes != -1) {
            detailImageView.setImageResource(itemImageRes)
        }
    }
}
