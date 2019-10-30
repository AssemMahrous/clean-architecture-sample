package net.mobiquity.main

import android.os.Bundle
import net.mobiquity.R
import net.mobiquity.core.platform.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun getBaseViewModel(): MainViewModel = mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
