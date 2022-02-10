package wiki.scene.mvidemo

import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.hjq.bar.TitleBar
import wiki.scene.base.BaseFragment
import wiki.scene.mvidemo.databinding.FragmentMainBinding
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val mBinding: FragmentMainBinding by viewBinding()
    override fun initTitleBar(): TitleBar {
        return mBinding.includeTitle.titleBar.apply {
            title = "主页"
        }
    }

    override fun hasTitleBarBack(): Boolean {
        return false
    }

    override fun initView() {
        mBinding.btnLogin
            .setOnClickListener {
                findNavController().navigate(R.id.action_to_login)
            }
    }

    override fun invalidate() {

    }
}