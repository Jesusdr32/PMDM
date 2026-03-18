package com.example.firstfragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var mtMain : MaterialToolbar
    private lateinit var imMorfeo : ImageView
    private lateinit var tv2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mtMain = findViewById(R.id.mtMain)
        imMorfeo = findViewById(R.id.imMorfeo)
        tv2 = findViewById(R.id.tv2)

        setSupportActionBar(mtMain)
        supportActionBar!!.hide()
        tv2.setOnClickListener {
            supportActionBar!!.show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mtMain.inflateMenu(R.menu.mimenu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "roja" -> tv2.text = "Ha elegido la pastilla roja."
            "azul" -> tv2.text = "Ha elegido la pastilla azul."
            "salir" -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}