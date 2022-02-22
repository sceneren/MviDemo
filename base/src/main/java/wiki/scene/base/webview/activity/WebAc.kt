package wiki.scene.base.webview.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.webkit.WebView
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils
import com.casad.common.resources.toXmlString
import com.hjq.bar.TitleBar
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import com.kongzue.dialogx.dialogs.BottomMenu
import wiki.scene.base.R
import wiki.scene.base.base.BaseActivity
import wiki.scene.base.databinding.BaseTitleBarViewBinding
import wiki.scene.base.databinding.BaseWebAcWebBinding
import wiki.scene.base.databinding.BaseWebAcWebTitleBarViewRightActionViewBinding
import wiki.scene.base.titlebar.addCustomRightView
import wiki.scene.base.webview.layout.WebLayout

class WebAc : BaseActivity(R.layout.base_web_ac_web) {

    companion object {
        fun start(context: Context, url: String) {
            val intent = Intent(context, WebAc::class.java)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }

    private var url: String? = null

    private var agentWeb: AgentWeb? = null
    private var _mBinding: BaseWebAcWebBinding? = null
    private val mBinding get() = _mBinding!!

    private val mWebViewClient: WebViewClient = object : WebViewClient() {

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        }
    }
    private val mWebChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            getTitleBarViewBinding(mBinding).libBaseTvTitleBar.title = title
        }
    }

    override fun hasTitleBarBack(): Boolean {
        return true
    }

    override fun injectTitleBarViewBinding(): BaseTitleBarViewBinding {
        return BaseTitleBarViewBinding.bind(mBinding.root)
    }

    override fun initTitleBarView(titleBar: TitleBar) {
        super.initTitleBarView(titleBar)
        val rightActionView = View.inflate(
            mActivity,
            R.layout.base_web_ac_web_title_bar_view_right_action_view,
            null
        )

        val actionBinding = BaseWebAcWebTitleBarViewRightActionViewBinding.bind(rightActionView)
        actionBinding.rlClose.setOnClickListener {
            finish()
        }

        actionBinding.rlMore.setOnClickListener {

            BottomMenu.show(
                arrayOf(
                    R.string.base_refresh.toXmlString(),
                    R.string.base_open_in_browser.toXmlString()
                )
            )
                .setCancelButton(R.string.base_cancel.toXmlString())
                .setOnMenuItemClickListener { _, _, index ->
                    when (index) {
                        0 -> {
                            this@WebAc.agentWeb!!.urlLoader.reload()
                        }
                        1 -> {
                            try {
                                val i = Intent(Intent.ACTION_VIEW)
                                i.data = Uri.parse(url)
                                startActivity(i)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                        }
                    }
                    false
                }
        }
        titleBar.addCustomRightView(rightActionView, 100F, 30F)
    }

    override fun onLeftClick(view: View?) {
        super.onLeftClick(view)
        backOrFinish()
    }

    override fun beforeInitView() {
        super.beforeInitView()
        _mBinding = BaseWebAcWebBinding.inflate(layoutInflater)
        setContentView(_mBinding!!.root)
    }

    override fun initView() {
        super.initView()
        LogUtils.d("webUrl=$url")
        url = intent.getStringExtra("url")
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(mBinding.parent, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebChromeClient(mWebChromeClient)
            .setWebViewClient(mWebViewClient)
            .setMainFrameErrorView(com.just.agentweb.R.layout.agentweb_error_page, -1)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setWebLayout(WebLayout(this))
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK) //打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl() //拦截找不到相关页面的Scheme
            .createAgentWeb()
            .ready()
            .go(url)
    }

    override fun onBackPressed() {
        backOrFinish()
    }

    private fun backOrFinish() {
        if (agentWeb == null) {
            finish()
        } else {
            if (agentWeb!!.webCreator.webView.canGoBack()) {
                agentWeb!!.back()
            } else {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        agentWeb?.webLifeCycle?.onResume()
    }

    override fun onPause() {
        super.onPause()
        agentWeb?.webLifeCycle?.onPause()
    }

    override fun lazyLoadTime(): Long {
        return 300L
    }

    override fun onDestroy() {
        super.onDestroy()
        agentWeb?.webLifeCycle?.onDestroy()
    }
}