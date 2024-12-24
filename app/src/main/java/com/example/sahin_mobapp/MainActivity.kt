package com.example.sahin_mobapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the initial fragment to FragmentA
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<FragmentA>(R.id.fragmentContainer)
            }
        }

        // Toggle fragments on button click
        findViewById<View>(R.id.toggleButton).setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            supportFragmentManager.commit {
                if (currentFragment is FragmentA) {
                    replace<FragmentB>(R.id.fragmentContainer)
                    addToBackStack(null) // Add FragmentB to back stack
                } else {
                    supportFragmentManager.popBackStack() // Go back to FragmentA
                }
            }
        }
    }
}
