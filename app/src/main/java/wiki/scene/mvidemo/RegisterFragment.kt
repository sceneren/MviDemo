package wiki.scene.mvidemo

import com.hjq.bar.TitleBar
import wiki.scene.base.BaseFragment
import wiki.scene.mvidemo.databinding.FragmentRegisterBinding
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private val mBinding: FragmentRegisterBinding by viewBinding()

    override fun initTitleBar(): TitleBar {
        return mBinding.includeTitle
            .titleBar
            .apply {
                title = "注册"
            }
    }

    override fun hasTitleBarBack(): Boolean {
        return true
    }

    override fun initView() {

    }

    override fun invalidate() {

    }
}