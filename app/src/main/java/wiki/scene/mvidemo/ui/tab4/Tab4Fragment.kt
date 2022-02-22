package wiki.scene.mvidemo.ui.tab4

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.chad.library.adapter.base.BaseBinderAdapter
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentTab4Binding
import wiki.scene.mvidemo.ui.login.LoginFragment
import wiki.scene.mvidemo.ui.tab4.vm.Tab4ViewModel
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class Tab4Fragment : BaseFragment(R.layout.fragment_tab_4) {

    private val mBinding: FragmentTab4Binding by viewBinding()
    private val viewModel: Tab4ViewModel by fragmentViewModel()

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
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val mAdapter = BaseBinderAdapter().apply {
            addItemBinder(Tab4ItemBinder())
            for (i in 0..30) {
                addData("数据${i}")
            }
            setOnItemClickListener { _, _, position ->
                requireParentFragment().findNavController()
                    .navigate(R.id.action_to_login
                        //, LoginFragment.arg(position)
            )
            }
        }
        mBinding.recyclerView.adapter = mAdapter

    }

    override fun injectLoadServiceView(): View {
        return mBinding.recyclerView
    }

    override fun invalidate() {
        withState(viewModel) {
            when (it.uiState) {
                0 -> {
                    showLoadingPage()
                }
                1 -> {
                    //showEmptyPage()
                }
                2 -> {
                    showErrorPage()
                }
                3 -> {
                    showSuccessPage()
                }
            }
        }
    }

    override fun onRetryBtnClick() {
        super.onRetryBtnClick()
        viewModel.getData()
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