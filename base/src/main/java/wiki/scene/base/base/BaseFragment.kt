package wiki.scene.base.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.gyf.immersionbar.ktx.immersionBar
import com.hjq.bar.OnTitleBarListener
import com.hjq.bar.TitleBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes), MavericksView,
    IUiView,
    OnTitleBarListener {

    lateinit var mContext: Context
    lateinit var mActivity: Activity

    //是否已经加载过数据
    private var isFirst = false

    abstract fun initTitleBar(): TitleBar?

    abstract fun hasTitleBarBack(): Boolean

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    open fun initData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireContext()
        mActivity = requireActivity()
        isFirst = true
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

    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lifecycleScope.launch {
                flow {
                    delay(lazyLoadTime())
                    emit(true)
                }.collect {
                    beforeLoadData()
                    loadData()
                    isFirst = false
                }
            }
        }
    }

    override fun beforeLoadData() {

    }

    override fun loadData() {
    }

    override fun onLeftClick(view: View) {
        findNavController().navigateUp()
    }

    override fun onTitleClick(view: View) {

    }

    override fun onRightClick(view: View) {

    }

    override fun lazyLoadTime(): Long {
        //大于界面的跳转动画的时间就行
        return 300L
    }


}