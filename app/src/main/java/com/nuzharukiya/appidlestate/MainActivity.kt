package com.nuzharukiya.appidlestate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.nuzharukiya.appidlestate.base.BaseActivity

/**
 * Created by nuzharukiya <r.nuzha@gmail.com> on 11/08/22, 11:01 am
 * Version 1.0,
 * Created for Self.
 */
class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1)
            .setOnClickListener(this)
        findViewById<Button>(R.id.button2)
            .setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button1 -> {
//                Toast.makeText(this, "Button1 clicked", Toast.LENGTH_SHORT).show()
                with(Intent(this, PurpleActivity::class.java)) {
                    startActivity(this)
                }
            }
            R.id.button2 -> {
//                Toast.makeText(this, "Button2 clicked", Toast.LENGTH_SHORT).show()
                with(Intent(this, TealActivity::class.java)) {
                    startActivity(this)
                }
            }
            else -> {}
        }
    }
}