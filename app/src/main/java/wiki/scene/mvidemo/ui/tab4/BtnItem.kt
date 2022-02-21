package wiki.scene.mvidemo.ui.tab4

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.blankj.utilcode.util.LogUtils
import wiki.scene.mvidemo.databinding.FragmentTab4ItemBinding
import wiki.scene.viewbinding.viewbindingutil.viewBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class BtnItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: FragmentTab4ItemBinding by viewBinding()

    init {
        orientation = VERTICAL
    }

    @TextProp
    fun setText(title: CharSequence?) {
        binding.btnTest.text = title
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        LogUtils.e("clickListener:${clickListener.toString()}")
        setOnClickListener(clickListener)
    }
}