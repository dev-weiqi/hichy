package dev.weiqi.hichysample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.weiqi.hichysample.fragment.BlueFragment
import dev.weiqi.hichysample.fragment.RedFragment

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_red, RedFragment.newInstance())
            .commit()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_blue, BlueFragment.newInstance())
            .commit()
    }
}