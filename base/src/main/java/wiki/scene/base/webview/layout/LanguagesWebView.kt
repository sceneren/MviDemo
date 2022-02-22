package wiki.scene.base.webview.layout

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.hjq.language.MultiLanguages

class LanguagesWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {
    init {
        // 修复 WebView 初始化时会修改 Activity 语种配置的问题
        MultiLanguages.updateAppLanguage(context)
    }
}