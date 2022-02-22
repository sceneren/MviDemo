package wiki.scene.mvidemo.ui.tab1

import android.os.Bundle
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.base.webview.activity.WebAc
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentTab1Binding
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class Tab1Fragment : BaseFragment(R.layout.fragment_tab_1) {

    private val mBinding: FragmentTab1Binding by viewBinding()

    override fun initTitleBar(): TitleBar {
        return mBinding.includeTitle
            .titleBar
            .apply {
                title = "Tab1"
            }
    }

    override fun hasTitleBarBack(): Boolean {
        return false
    }

    override fun initView() {
        mBinding.tvTab1.setOnClickListener {
            WebAc.start(mContext, "https://www.youku.com/")
        }

    }

    override fun invalidate() {
    }

    companion object {
        fun newInstance(): Tab1Fragment {
            val args = Bundle()

            val fragment = Tab1Fragment()
            fragment.arguments = args
            return fragment
        }
    }

}