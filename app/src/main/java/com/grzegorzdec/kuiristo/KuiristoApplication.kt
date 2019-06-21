package com.grzegorzdec.kuiristo

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class KuiristoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}