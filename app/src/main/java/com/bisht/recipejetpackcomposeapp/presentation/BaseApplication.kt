package com.bisht.recipejetpackcomposeapp.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    val dark = mutableStateOf(false)
    fun toggleLightTheme(){
        dark.value = !dark.value
    }
}