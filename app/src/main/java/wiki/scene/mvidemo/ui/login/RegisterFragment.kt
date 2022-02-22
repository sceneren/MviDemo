package wiki.scene.mvidemo.ui.login

import androidx.navigation.fragment.findNavController
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.mvidemo.R
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
        mBinding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_to_main)
        }
    }

    override fun invalidate() {

    }
}