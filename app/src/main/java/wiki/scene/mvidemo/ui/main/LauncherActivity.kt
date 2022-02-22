package wiki.scene.mvidemo.ui.main

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import wiki.scene.base.base.BaseActivity
import wiki.scene.base.base.BaseApp
import wiki.scene.mvidemo.R

class LauncherActivity : BaseActivity(R.layout.activity_launcher) {
    override fun initView() {
        super.initView()
        BaseApp.instance.laterInit()

        lifecycleScope.launch {
            flow {
                delay(2000L)
                emit(true)
            }.collect {
                MainActivity.start(mActivity)
            }
        }

    }
}