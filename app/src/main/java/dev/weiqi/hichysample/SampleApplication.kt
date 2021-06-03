package dev.weiqi.hichysample

import android.app.Application
import dev.weiqi.hichy.Hichy

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Hichy.init(this)
    }
}