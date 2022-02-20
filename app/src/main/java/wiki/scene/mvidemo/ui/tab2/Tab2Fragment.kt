package wiki.scene.mvidemo.ui.tab2

import android.os.Bundle
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentTab2Binding
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class Tab2Fragment : BaseFragment(R.layout.fragment_tab_2) {

    private val mBinding: FragmentTab2Binding by viewBinding()

    override fun initTitleBar(): TitleBar {
        return mBinding.includeTitle
            .titleBar
            .apply {
                title = "Tab2"
            }
    }

    override fun hasTitleBarBack(): Boolean {
        return false
    }

    override fun initView() {
    }

    override fun invalidate() {
    }

    companion object {
        fun newInstance(): Tab2Fragment {
            val args = Bundle()
            val fragment = Tab2Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}