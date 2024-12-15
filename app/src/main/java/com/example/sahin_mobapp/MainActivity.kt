package com.example.sahin_mobapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    private var showingFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentA())
                .commit()
        }

        findViewById<Button>(R.id.buttonToggle).setOnClickListener {
            toggleFragment()
        }
    }

    private fun toggleFragment() {
        val newFragment: Fragment = if (showingFragmentA) FragmentB() else FragmentA()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, newFragment)
            .commit()

        showingFragmentA = !showingFragmentA
    }
}
