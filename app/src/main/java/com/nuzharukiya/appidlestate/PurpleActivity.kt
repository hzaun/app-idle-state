package com.nuzharukiya.appidlestate

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.nuzharukiya.appidlestate.base.BaseActivity

/**
 * Created by nuzharukiya <r.nuzha@gmail.com> on 11/08/22, 11:01 am
 * Version 1.0,
 * Created for Self.
 */
class PurpleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purple)

        findViewById<Button>(R.id.button)
            .setOnClickListener {
                Toast.makeText(this, "Button1 clicked", Toast.LENGTH_SHORT).show()
            }
    }
}