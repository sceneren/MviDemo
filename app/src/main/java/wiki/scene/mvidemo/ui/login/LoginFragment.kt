package wiki.scene.mvidemo.ui.login

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.blankj.utilcode.util.LogUtils
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentLoginBinding
import wiki.scene.mvidemo.ui.login.viewmodel.LoginViewModel
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val mBinding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by fragmentViewModel()
    override fun initTitleBar(): TitleBar {
        return mBinding.includeTitle.titleBar.apply {
            title = "登录"
        }
    }

    override fun hasTitleBarBack(): Boolean {
        return true
    }

    override fun initView() {

        LogUtils.e("LoginFragment.initView")

        mBinding.btnLogin.setOnClickListener {
            val username = mBinding.username.text.toString()
            val password = mBinding.password.text.toString()
            viewModel.login(username, password)

            withState(viewModel) { state ->
                Toast.makeText(
                    this@LoginFragment.context,
                    state.userInfo,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        mBinding.btnRegister
            .setOnClickListener {
                findNavController().navigate(R.id.action_to_register)
            }
    }

    override fun invalidate() {
        //需要监听一直变化的才写在这
    }

    override fun onResume() {
        super.onResume()
        LogUtils.e("LoginFragment.onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.e("LoginFragment.onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.e("LoginFragment.onDestroyView")
    }
}