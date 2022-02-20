package wiki.scene.mvidemo.ui.tab4

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentTab4Binding
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class Tab4Fragment : BaseFragment(R.layout.fragment_tab_4) {

    private val mBinding: FragmentTab4Binding by viewBinding()

    override fun initTitleBar(): TitleBar {
        return mBinding.includeTitle
            .titleBar
            .apply {
                title = "Tab4"
            }
    }

    override fun hasTitleBarBack(): Boolean {
        return false
    }

    override fun initView() {
        mBinding.btnToLogin
            .setOnClickListener {
               requireActivity().findNavController(R.id.nav_host_fragment).navigate(R.id.action_to_login)
            }
    }

    override fun invalidate() {
    }

    companion object {
        fun newInstance(): Tab4Fragment {
            val args = Bundle()
            val fragment = Tab4Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}