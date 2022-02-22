package wiki.scene.base.webview.layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.IWebLayout
import wiki.scene.base.R

/**
 *
 * @Description:    web实现类
 * @Author:         scene
 * @CreateDate:     2021/7/2 09:54
 * @UpdateUser:
 * @UpdateDate:     2021/7/2 09:54
 * @UpdateRemark:
 * @Version:        1.0.0
 */
class WebLayout(activity: AppCompatActivity) : IWebLayout<LanguagesWebView, ViewGroup> {

    private val mWebView by lazy {
        LayoutInflater.from(activity)
            .inflate(R.layout.base_web_layout_web, null) as LanguagesWebView
    }

    override fun getLayout(): ViewGroup {
        return mWebView
    }

    override fun getWebView(): LanguagesWebView {
        return mWebView
    }
}