package wiki.scene.mvidemo.ui.main

import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.flyco.tablayout.listener.CustomTabEntity
import com.hjq.bar.TitleBar
import wiki.scene.base.BaseFragment
import wiki.scene.base.tab.MainTabEntity
import wiki.scene.base.tab.MainTabSelectListener
import wiki.scene.base.tab.bind
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.FragmentMainBinding
import wiki.scene.mvidemo.ui.tab1.Tab1Fragment
import wiki.scene.mvidemo.ui.tab2.Tab2Fragment
import wiki.scene.mvidemo.ui.tab3.Tab3Fragment
import wiki.scene.mvidemo.ui.tab4.Tab4Fragment
import wiki.scene.viewbinding.viewbindingutil.viewBinding
import java.util.ArrayList

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val mBinding: FragmentMainBinding by viewBinding()

    private val tabList = mutableListOf<CustomTabEntity>()
    private val fragmentArray = mutableListOf<Fragment>()

    override fun initTitleBar(): TitleBar? {
        return null
    }

    override fun hasTitleBarBack(): Boolean {
        return false
    }

    override fun initView() {
        LogUtils.e("initView")
        tabList.add(
            MainTabEntity(
                "Tab1",
                R.drawable.tab_home_select,
                R.drawable.tab_home_unselect
            )
        )
        tabList.add(
            MainTabEntity(
                "Tab2",
                R.drawable.tab_speech_select,
                R.drawable.tab_speech_unselect
            )
        )
        tabList.add(
            MainTabEntity(
                "Tab3",
                R.drawable.tab_contact_select,
                R.drawable.tab_contact_unselect
            )
        )
        tabList.add(
            MainTabEntity(
                "Tab4",
                R.drawable.tab_more_select,
                R.drawable.tab_more_unselect
            )
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

    override fun invalidate() {

    }
}