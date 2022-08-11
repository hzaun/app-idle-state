package com.nuzharukiya.appidlestate

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.nuzharukiya.appidlestate.base.BaseActivity

class TealActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teal)

        findViewById<Button>(R.id.button1)
            .setOnClickListener {
                Toast.makeText(this, "Button1 clicked", Toast.LENGTH_SHORT).show()
            }
        findViewById<Button>(R.id.button2)
            .setOnClickListener {
                Toast.makeText(this, "Button2 clicked", Toast.LENGTH_SHORT).show()
            }
    }
}