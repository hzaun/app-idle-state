package com.nuzharukiya.appidlestate.base

import com.nuzharukiya.appidlestate.data.AppState

/**
 * Created by nuzharukiya <r.nuzha@gmail.com> on 11/08/22, 11:21 pm
 * Version 1.0,
 * Created for Self.
 */
interface StateListener {
    var appState: AppState

    fun onStateChanged(newState: AppState, oldState: AppState)
}