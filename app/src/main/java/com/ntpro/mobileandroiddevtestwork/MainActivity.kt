package com.ntpro.mobileandroiddevtestwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntpro.mobileandroiddevtestwork.databinding.ActivityMainBinding
import com.ntpro.mobileandroiddevtestwork.presentation.screen.FragmentTables

lateinit var APP_ACTIVITY: MainActivity
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val mBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        APP_ACTIVITY = this

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, FragmentTables())
                .commit()
        }
    }
}