package wiki.scene.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.gyf.immersionbar.ktx.immersionBar
import com.hjq.bar.OnTitleBarListener
import com.hjq.bar.TitleBar

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes), MavericksView,
    OnTitleBarListener {

    abstract fun initTitleBar(): TitleBar?

    abstract fun hasTitleBarBack(): Boolean

    abstract fun initView()

    open fun initData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        immersionBar {
            statusBarDarkFont(true)
            navigationBarDarkIcon(true)
            navigationBarColorInt(Color.WHITE)
            initTitleBar()?.run {
                titleBar(this)
                setOnTitleBarListener(this@BaseFragment)
                if (hasTitleBarBack()) {
                    setLeftIcon(com.hjq.bar.R.drawable.bar_arrows_left_black)
                } else {
                    leftIcon = null
                }
            }
        }

        initView()

        view.postDelayed({ initData() }, 300L)
    }

    override fun onLeftClick(view: View) {
        findNavController().popBackStack()
    }

    override fun onTitleClick(view: View) {

    }

    override fun onRightClick(view: View) {

    }


}