package com.example.miproyecto.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miproyecto.R
import com.example.miproyecto.ui.login.LoginActivity
import com.example.miproyecto.ui.main.adapter.ViewPagerAdapter
import com.example.miproyecto.utils.SessionManager
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = sessionManager.getUsername()

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
            when (position) {
                0 -> {
                    tab.text = "Home"
                    tsb.setIcon(R.drawable.ic_home)
                }
                1 -> {
                    tab.text = "Productos"
                    tab.setIcon(R.drawable.ic_products)
                }
                2 -> {
                    tab.text = "Mi Carro"
                    tab.setIcon(R.drawable.ic_cart)
                }
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        manuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_layout) {
            sessionManager.clearSession()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        return true
    }
}