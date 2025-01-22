package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest

class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var adapter: ItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView: SearchView = view.findViewById(R.id.searchView)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        adapter = ItemAdapter(
            itemList = emptyList(),
            onItemClicked = { item -> navigateToDetail(item) },
            onLikeClicked = { item ->
                Toast.makeText(requireContext(), "You liked: ${item.name}", Toast.LENGTH_SHORT).show()
            },
            onShareClicked = { item ->
                Toast.makeText(requireContext(), "You shared: ${item.name}", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.recipeList.collectLatest { recipes ->
                adapter.updateItems(recipes)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateQuery(newText.orEmpty())
                return true
            }
        })

        viewModel.loadAllRecipes()
    }

    private fun navigateToDetail(item: Recipe) {
        val intent = Intent(requireContext(), ItemDetailActivity::class.java).apply {
            putExtra("ITEM_ID", item.itemId)
            putExtra("ITEM_NAME", item.name)
            putExtra("ITEM_IMAGE_RES", item.imageResource)
        }
        startActivity(intent)
    }
}
