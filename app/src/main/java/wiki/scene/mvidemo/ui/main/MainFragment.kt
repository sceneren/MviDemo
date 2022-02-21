package wiki.scene.mvidemo.ui.main

import androidx.fragment.app.Fragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.flyco.tablayout.listener.CustomTabEntity
import com.hjq.bar.TitleBar
import wiki.scene.base.base.BaseFragment
import wiki.scene.base.tab.MainTabSelectListener
import wiki.scene.base.tab.bind
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentMainBinding
import wiki.scene.mvidemo.ui.main.vm.MainViewModel
import wiki.scene.mvidemo.ui.tab1.Tab1Fragment
import wiki.scene.mvidemo.ui.tab2.Tab2Fragment
import wiki.scene.mvidemo.ui.tab3.Tab3Fragment
import wiki.scene.mvidemo.ui.tab4.Tab4Fragment
import wiki.scene.viewbinding.viewbindingutil.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val mBinding: FragmentMainBinding by viewBinding()

    private val viewModel: MainViewModel by fragmentViewModel()


    override fun initTitleBar(): TitleBar? {
        return null
    }

    override fun hasTitleBarBack(): Boolean {
        return false
    }

    override fun initView() {
        withState(viewModel) {
            val tabList = mutableListOf<CustomTabEntity>()
            val fragmentArray = mutableListOf<Fragment>()
            tabList.add(
                it.tabs[0]
            )
            tabList.add(
                it.tabs[1]
            )
            tabList.add(
                it.tabs[2]
            )
            tabList.add(
                it.tabs[3]
            )

            fragmentArray.add(
                0,
                Tab1Fragment.newInstance()
            )
            fragmentArray.add(
                1,
                Tab2Fragment.newInstance()
            )
            fragmentArray.add(
                2,
                Tab3Fragment.newInstance()
            )
            fragmentArray.add(
                3,
                Tab4Fragment.newInstance()
            )

            mBinding.tabLayout.setTabData(tabList as ArrayList<CustomTabEntity>)
            mBinding.tabLayout.bind(
                this,
                mBinding.viewPager,
                fragmentArray,
                object : MainTabSelectListener {
                    override fun onTabSelect(position: Int) {
                        super.onTabSelect(position)
                        mBinding.tabLayout.hideMsg(position)
                    }
                })
            mBinding.tabLayout.showMsg(1, 999)
            mBinding.tabLayout.setMsgMargin(1, -10F, 0F)
        }

    }

    override fun invalidate() {

    }
}