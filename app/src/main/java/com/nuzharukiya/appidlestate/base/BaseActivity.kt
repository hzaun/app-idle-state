package com.nuzharukiya.appidlestate.base

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nuzharukiya.appidlestate.data.AppState
import com.nuzharukiya.appidlestate.utils.CancellableTaskExecutor
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

/**
 * Created by nuzharukiya <r.nuzha@gmail.com> on 11/08/22, 11:05 am
 * Version 1.0,
 * Created for Self.
 */
open class BaseActivity :
    AppCompatActivity(),
    StateListener
{
    private lateinit var worker: ScheduledExecutorService
    private lateinit var cancellableWorker: CancellableTaskExecutor
    private lateinit var execution: CancellableTaskExecutor.Execution

    private val TIMEOUT: Long = 4000
    override var appState: AppState = AppState.ACTIVE
    private val runnable = Runnable {
        onStateChanged(AppState.IDLE, appState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        worker = Executors.newSingleThreadScheduledExecutor()
        cancellableWorker = CancellableTaskExecutor(worker, Logger.getAnonymousLogger())
        execution = cancellableWorker.schedule(runnable, TIMEOUT, TimeUnit.MILLISECONDS)
    }

    override fun onStateChanged(newState: AppState, oldState: AppState) {
        appState = newState
        runOnUiThread {
            Toast.makeText(this, "App idle for ${TIMEOUT / 1000} seconds", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        resetHandler()
        return super.dispatchTouchEvent(ev)
    }

    private fun resetHandler() {
        execution.cancel()
        execution = cancellableWorker.schedule(runnable, TIMEOUT, TimeUnit.MILLISECONDS)
    }

    override fun onPause() {
        super.onPause()
        execution.cancel()
    }

    override fun onResume() {
        super.onResume()
        resetHandler()
    }
}