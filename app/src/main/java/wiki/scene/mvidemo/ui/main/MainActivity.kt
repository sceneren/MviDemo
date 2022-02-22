package wiki.scene.mvidemo.ui.main

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import wiki.scene.base.base.BaseActivity
import wiki.scene.mvidemo.R

class MainActivity : BaseActivity(R.layout.activity_main) {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            ActivityUtils.finishOtherActivities(MainActivity::class.java)
        }
    }

    override fun enableSlideBack(): Boolean {
        return false
    }

    override fun isDoubleClickExit(): Boolean {
        return true
    }
}