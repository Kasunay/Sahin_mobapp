package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    private val sampleItems = listOf(
        Item(1, "Tonkotsu Ramen", "Is a Japanese noodle soup dish that originated in Fukuoka, Fu..", R.drawable.ramen),
        Item(2, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake made fr..", R.drawable.takoyaki),
        Item(3, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare..", R.drawable.udon)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ItemAdapter(
            itemList = sampleItems,
            onItemClicked = { item ->
                navigateToDetail(item)
            },
            onLikeClicked = { item ->
                Toast.makeText(this, "You liked: ${item.name}", Toast.LENGTH_SHORT).show()
            },
            onShareClicked = { item ->
                Toast.makeText(this, "You shared: ${item.name}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.adapter = adapter
    }

    private fun navigateToDetail(item: Item) {
        val intent = Intent(this, ItemDetailActivity::class.java).apply {
            putExtra("ITEM_ID", item.itemId)
            putExtra("ITEM_NAME", item.name)
            putExtra("ITEM_IMAGE_RES", item.imageResource)
        }
        startActivity(intent)
    }
}
