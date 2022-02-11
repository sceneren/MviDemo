package wiki.scene.mvidemo.ui.tab3

import android.os.Bundle
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentTab3Binding
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class Tab3Fragment : BaseFragment(R.layout.fragment_tab_3) {

    private val mBinding: FragmentTab3Binding by viewBinding()

    override fun initTitleBar(): TitleBar {
        return mBinding.includeTitle
            .titleBar
            .apply {
                title = "Tab3"
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
        fun newInstance(): Tab3Fragment {
            val args = Bundle()
            val fragment = Tab3Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}