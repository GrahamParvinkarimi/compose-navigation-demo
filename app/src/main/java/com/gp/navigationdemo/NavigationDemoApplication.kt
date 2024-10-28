package com.gp.navigationdemo

import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NavigationDemoApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}